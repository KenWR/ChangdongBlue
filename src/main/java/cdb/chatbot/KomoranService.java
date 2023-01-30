package cdb.chatbot;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cdb.domain.etc.enums.AttendanceStudentStatus;
import cdb.domain.repository.AttendanceStudentRepository;
import cdb.domain.repository.EmployeeRepository;
import cdb.domain.repository.StudentRepository;
import cdb.domain.repository.TeacherRepository;
import kr.co.shineware.nlp.komoran.core.Komoran;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KomoranService {

	private final Komoran komoran;

	private final EmployeeRepository emp;

	private final TeacherRepository tea;

	private final ChatBotIntentionRepository intention;

	private final StudentRepository sr;

	private final AttendanceStudentRepository asr;

	// 입력된 목적어를 하나씩 파악하여 DB에서 검색하기위해 decisionTree()메서드로 전달
	private AnswerEntity analyzeToken(final Set<String> nouns) {

		// 1차의도가 존재하는지 파악
		for (final String token : nouns) {

			final var result = decisionTree(token, null);
			if (result.isEmpty()) continue;
			System.out.println(">>>>1차:" + token);
			final Set<String> next = nouns.stream().collect(Collectors.toSet());
			next.remove(token);

			String keyword = null;
			if (token.contains("직원") || token.contains("사원")) {
				final var phone = analyzeTokenIsPhone(next);
				if (phone == null)
					keyword = "해당 직원을 찾을수 없습니다.";
				else keyword = phone.getDept() + " / " + phone.getEmpName() + " / " + phone.getPhone();
			}

			if (token.contains("출석")) {
				final var phone = analyzeTokenIsPhone3(next);
				if (phone == null)
					keyword = "학생의 이름을 입력해 주세요";
				else
					keyword = phone.getStuName() + " / " + phone.getPhone() + " / " + phone.getDay() + " / " + phone.getStatus();
			}

			// 2차분석 메서드
			return analyzeToken(next, result).keyword(keyword);
		}
		// 분석 명사들이 등록한 의도와 일치하는게 존재하지 않을경우 null
		return null;

	}

	// 1차의도가 존재하면
	// 하위의도가 존재하는지 파악
	private AnswerEntity analyzeToken(final Set<String> next, final Optional<ChatBotIntentionEntity> upper) {
		for (final String token : next) {
			// 1차의도를 부모로하는 토큰이 존재하는지 파악
			final var result = decisionTree(token, upper.get());
			if (result.isEmpty()) continue;
			System.out.println(">>>>2차:" + token);
			return result.get().getAnswer();// 1차-2차 존재하는경우 답변
		}
		return upper.get().getAnswer();// 1차만 존재하는 답변
	}

	// 전화문의인경우 DB에서 사원을 을 찾아서 처리
	private PhoneInfoDTO analyzeTokenIsPhone(final Set<String> next) {
		for (final String name : next) {
			final var e = emp.findByName(name);
			if (e.isEmpty()) continue;
			// 존재하면
			final var deptName = e.get().getDepartment().getTitle();
			final var phone = e.get().getPhone();
			final var empName = e.get().getName();
			return PhoneInfoDTO.builder().dept(deptName).phone(phone).empName(empName).build();

		}
		return null;
	}

	// 학생 출석 여부
	private PhoneInfoDTO analyzeTokenIsPhone3(final Set<String> next) {
		for (final String name : next) {
			final var s = sr.findByName(name);
			final var as = asr.findByNo(s.get().getNo());
			if (s.isEmpty() && as.get().getStatus() != AttendanceStudentStatus.ON) continue;
			// 존재하면
			final var stuName = s.get().getName();
			final var phone = s.get().getPhone();
			final var status = as.get().getStatus();
			final var day = as.get().getDay();
			return PhoneInfoDTO.builder().stuName(stuName).phone(phone).status(status).day(day).build();

		}
		return null;
	}

	// 의도가 존재하는지 DB에서 파악
	private Optional<ChatBotIntentionEntity> decisionTree(final String token, final ChatBotIntentionEntity upper) {
		return intention.findByNameAndUpper(token, upper);
	}

	public String nlpAnalyze(final String message) {

		final var result = komoran.analyze(message);

		// 문자에서 명사들만 추출한 목록 중복제거해서 set
		final Set<String> nouns = result.getNouns().stream().collect(Collectors.toSet());
		nouns.forEach(noun -> { System.out.println(">>>:" + noun); });
		// 메세지에서 명사추출

		var answer = "입력한  정보를 찾을수 없습니다.";
		final var ar = analyzeToken(nouns);
		if (ar != null) {
			answer = ar.getContent();
			if (ar.getKeyword() != null) answer += ar.getKeyword();

		}
		return answer;
	}

}