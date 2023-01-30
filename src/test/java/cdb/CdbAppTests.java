package cdb;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import cdb.chatbot.AnswerEntity;
import cdb.chatbot.AnswerRepository;
import cdb.chatbot.ChatBotIntentionEntity;
import cdb.chatbot.ChatBotIntentionRepository;
import cdb.domain.entity.CourseEntity;
import cdb.domain.entity.DepartmentEntity;
import cdb.domain.entity.EmployeeEntity;
import cdb.domain.entity.StudentCourseEntity;
import cdb.domain.entity.StudentEntity;
import cdb.domain.entity.TeacherEntity;
import cdb.domain.etc.enums.CdbRole;
import cdb.domain.repository.AttendanceRepository;
import cdb.domain.repository.AttendanceStudentRepository;
import cdb.domain.repository.BoardRepository;
import cdb.domain.repository.CourseRepository;
import cdb.domain.repository.DepartmentRepository;
import cdb.domain.repository.DocRepository;
import cdb.domain.repository.EmployeeRepository;
import cdb.domain.repository.EvaluationRepository;
import cdb.domain.repository.ImgFileRepository;
import cdb.domain.repository.StudentCourseRepository;
import cdb.domain.repository.StudentRepository;
import cdb.domain.repository.TeacherRepository;
import cdb.utilities.aop.test.ParamValueAOP;
import cdb.utilities.aop.test.TimeMeasureAOP;

@Import({ TimeMeasureAOP.class, ParamValueAOP.class })
@SpringBootTest
@Transactional
@Commit
@Rollback(false)
class CdbAppTests {

	// @formatter:off
	//=================================================================
	@Autowired private AttendanceRepository ar;
	@Autowired private AttendanceStudentRepository asr;
	@Autowired private BoardRepository br;
	@Autowired private CourseRepository cr;
	@Autowired private DocRepository dcr;
	@Autowired private EmployeeRepository er;
	@Autowired private EvaluationRepository evr;
	@Autowired private ImgFileRepository ir;
	@Autowired private PasswordEncoder pe;
	@Autowired private StudentRepository sr;
	@Autowired private StudentCourseRepository scr;
	@Autowired private TeacherRepository tr;
	@Autowired private DepartmentRepository dr;
	@Autowired private AnswerRepository aswr;
	@Autowired private ChatBotIntentionRepository cir;
	@Test void 임시() {ar.flush();asr.flush();br.flush();cr.flush();dr.flush();evr.flush();ir.flush();er.flush();pe.encode("a");sr.flush();scr.flush();tr.flush();}
	// =====================================================================================================================
	public String krName() {final List<String> lastName = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신","권", "황", "안", "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽","성", "차", "주", "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함","변", "염", "양", "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기","반", "왕", "금", "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");final List<String> name = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노","누", "다", "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민","바", "박", "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수","숙", "순", "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원","월", "위", "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종","주", "준", "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁","현", "형", "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린","을", "비", "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추","걸", "삼", "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실","직", "흠", "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");Collections.shuffle(lastName);Collections.shuffle(name);return lastName.get(0) + name.get(0) + name.get(1);}
	public String krPhone() {final List<String> 번호 = Arrays.asList("010", "011", "012", "013");Collections.shuffle(번호);return 번호.get(0);}
	public String phoneNum() {return (int) (Math.random() * 8999) + 1000 + "";}
	private final String random = UUID.randomUUID().toString();
	// =================================================================
	// @formatter:on

	// =================================================================

	@Test
	void 강사_등록() {
		IntStream.range(0, 13).forEach(t -> {
			tr.save(TeacherEntity	.builder()
														.name(krName())
														.email(random + t)
														.phone(krPhone() + phoneNum() + phoneNum())
														.dateOfBirth(LocalDate.of((int) (Math.random() * 50) + 1950, (int) (Math.random() * 11) + 1,
																(int) (Math.random() * 27) + 1))
														.addr(random)
														.gender(t % 2 == 0)
														.password(pe.encode("12tea34"))
														.build()
														.addRole(CdbRole.TEACHER));
		});
	}

	@Test
	void 부서등록() {
		dr.save(DepartmentEntity.builder().title("인사부서").build());
		dr.save(DepartmentEntity.builder().title("수업지원부서").build());
		dr.save(DepartmentEntity.builder().title("수업운영부서").build());
		dr.save(DepartmentEntity.builder().title("재무부서").build());
		dr.save(DepartmentEntity.builder().title("물품관리부서").build());
	}

	@Test
	void 직원_등록() {
		IntStream.range(1, 25).forEach(t -> {
			er.save(EmployeeEntity.builder()
														.name(krName())
														.email(random + t)
														.phone(krPhone() + phoneNum() + phoneNum())
														.dateOfBirth(LocalDate.of((int) (Math.random() * 50) + 1950, (int) (Math.random() * 11) + 1,
																(int) (Math.random() * 27) + 1))
														.addr(random)
														.gender(t % 2 == 0)
														.department(null)
														.password(pe.encode("12emp34"))
														.build()
														.addRole(CdbRole.STAFF));
		});
	}

	@Test
	void 직원부서등록() {
		er.findById(2L).get().setDepartment(dr.findByTitle("인사부서").get());
		// er.findById(2L).get().setManager(null);

	}

	@Test
	void 코스() {
		IntStream.rangeClosed(1, 5).forEach(c -> {
			cr.save(CourseEntity.builder()
													.startDate(LocalDate.of(2023, 1, 3))
													.endDate(LocalDate.of(2023, 7, 3))
													.title("자바" + c)
													.teacher(tr.findById(1L).orElseThrow())
													.build());
		});

	}

	// @Test
	// void 학생_등록() {
	// IntStream.range(0, 1000).forEach(t -> {
	// sr.save(StudentEntity.builder()
	// .name(krName())
	// .email(random + t)
	// .phone(krPhone() + phoneNum() + phoneNum())
	// .dateOfBirth(LocalDate.of((int) (Math.random() * 50) + 1950, (int) (Math.random() * 11) + 1,
	// (int) (Math.random() * 27) + 1))
	// .addr(random)
	// .gender(t % 2 == 0)
	// .build());
	// });
	// }

	@Test
	@Transactional
	void 학생_등록() {
		IntStream.range(0, 1000).forEach(t -> {
			final var ss = sr.save(StudentEntity.builder()
																					.name(krName())
																					.email(random + t)
																					.phone(krPhone() + phoneNum() + phoneNum())
																					.dateOfBirth(LocalDate.of((int) (Math.random() * 50) + 1950,
																							(int) (Math.random() * 11) + 1, (int) (Math.random() * 27) + 1))
																					.addr(random)
																					.gender(t % 2 == 0)
																					.build());
			// 시현 - studentCourse엔티티에도 학생정보를 등록합니다. : 학생을 코스에 등록하기 위함
			// scr.save(StudentCourseEntity.builder().student(ss).build());
			// final var h = (int) (Math.random() * 2) + 8;
			// final var m = (int) (Math.random() * 59) + 1;
			// final var h2 = (int) (Math.random() * 5) + 15;
			// final var m2 = (int) (Math.random() * 59) + 1;
			// // 학생 출석 저장과정
			// asr.save(AttendanceStudentEntity.builder()
			// .day(LocalDate.now())
			// .offTime(LocalTime.of(h2, m2))
			// // .offTime(null)
			// .onTime(LocalTime.of(h, m))
			// // .onfTime(null)
			// .student(ss)
			// .build()
			// .addStaus(LocalTime.of(h, m), LocalTime.of(h2, m2)));

		});
	}

	@Test
	void 학생_출석부_생성() {

		final var cs = cr.findById(1L).orElseThrow();
		// final var at = asr.findAll();
		// cr.findById(null);
		// sr.findAll().forEach(d ->
		// scr.save(scr.save(StudentCourseEntity.builder().course(cs).student(d).build())));
		final var stt = sr.findAll();
		for (final StudentEntity a : stt) // asr.findAll().get(0).getSc().get(0).getStudent();
			scr.save(StudentCourseEntity.builder()
																	.student(a)
																	.course(cs)
																	// .attendance(asr.save(AttendanceStudentEntity.builder().day(LocalDate.now()).build()))
																	// - 연관관계 변경
																	.build());

		// scr.save(StudentCourseEntity.builder().course(cs).student(st).build());
		cr.findAllByStudentCoursesStudentNoIsNotNull().forEach(c -> c.getStudentCourses().forEach(sc -> scr.save(sc)));
	}

	@Test
	void 답변등록() {
		aswr.save(AnswerEntity.builder()
													// .content("해당 학생의 정보 입니다.")//.content("해당 사원의 정보 입니다.")
													.content("아직 출석하지 않은 학생들입니다.")
													.build());
	}
	// 여기서 순서대로 입력하는대로가 1L, 2L, 3L이 됩니다.

	// @Test
	// void 의도_등록() {
	// aswr.save(ChatBotIntentionEntity.builder()
	// .name("학생")
	// .answer(aswr.findById(1L).get())
	// .build());
	// }//학생의 정보를 얻어내기 위함

	// @Test
	// void 의도_등록() {
	// aswr.save(ChatBotIntentionEntity.builder()
	// .name("사원")//.name("직원")
	// .answer(aswr.findById(2L).get())
	// .build());
	// }//사원의 정보를 얻어내기 위함

	@Test
	void 의도_등록() {
		aswr.save(ChatBotIntentionEntity.builder()
																		.name("출석")//
																		.answer(aswr.findById(3L).get())
																		.build());
	}// 출석하지 않은 학생들을 알아내기 위함

	// @Test
	// void 답변등록0() {
	// aswr.save(AnswerEntity.builder().content("해당 학생의 정보 입니다.").build());
	// }

	// @Test
	// void 의도_등록0() {
	// aswr.save(ChatBotIntentionEntity.builder()
	// .name("학생")// 키워드
	// .answer(aswr.findById(2L).get())
	// .build());
	// }

}
