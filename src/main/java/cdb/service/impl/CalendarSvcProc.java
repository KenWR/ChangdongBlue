package cdb.service.impl;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cdb.domain.entity.CalendarEntity;
import cdb.domain.entity.TeacherEntity;
import cdb.domain.etc.enums.AttendanceStatus;
import cdb.domain.etc.enums.DocStatus;
import cdb.domain.repository.CalendarRepository;
import cdb.domain.repository.DocRepository;
import cdb.service.CalendarSvc;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarSvcProc implements CalendarSvc {

	private final DocRepository dr;

	private final CalendarRepository cr;

	@Override
	@Transactional
	public void regAbsenceAtt(long no, Model mv) {
		// 넘어온 no를 바탕으로 doc 검색해서 doc의 status를 APPROVAL에서 ENROLLMENT로 변경합니다.
		final var result = dr.findById(no).get();
		result.updateDocStatus(DocStatus.ENROLLMENT);

		// doc 작성자가 선생님인지 직원인지 구분합니다.
		final Optional<TeacherEntity> ifHeIsAteacher = Optional.ofNullable(result.getTeacher());

		if (ifHeIsAteacher.isEmpty()) {
			if (result.getDocType().toString().contains("VACATION"))
				cr.save(CalendarEntity.builder()
															.start(result.getStartDate())
															.end(result.getEndDate())
															.status(AttendanceStatus.VACATION)
															.employee(result.getEmployee())
															.build());
			else cr.save(CalendarEntity	.builder()
																	.start(result.getStartDate())
																	.end(result.getEndDate())
																	.status(AttendanceStatus.ABSENCE)
																	.employee(result.getEmployee())
																	.build());
		} else if (result.getDocType().toString().contains("VACATION"))
			cr.save(CalendarEntity.builder()
														.start(result.getStartDate())
														.end(result.getEndDate())
														.status(AttendanceStatus.VACATION)
														.teacher(result.getTeacher())
														.build());
		else cr.save(CalendarEntity	.builder()
																.start(result.getStartDate())
																.end(result.getEndDate())
																.status(AttendanceStatus.ABSENCE)
																.teacher(result.getTeacher())
																.build());// end else{}
	}// end regAbsenceAtt메소드

	@Override
	public List<Map<String, Object>> findAll() {
		final List<Map<String, Object>> result = new ArrayList<>();
		final var list = cr.findAll();

		for (var i = 0; i < list.size(); i++) {
			final Map<String, Object> map = new HashMap<>();
			final var name = list.get(i).getTeacher() != null ? list.get(i).getTeacher().getName()
					: list.get(i).getEmployee().getName();

			map.put("title", name + " " + list.get(i).getStart().format(DateTimeFormatter.ofPattern("MM/dd")) + " ~ "
					+ list.get(i).getEnd().format(DateTimeFormatter.ofPattern("MM/dd")));

			map.put("status", list.get(i).getStatus());
			map.put("start", list.get(i).getStart());
			map.put("end", list.get(i).getEnd().plusDays(1));
			// map.put("end", list.get(i).getEnd().atTime(LocalTime.MAX));
			result.add(map);
		}

		for (final Map<String, Object> r : result) System.out.println(">>>" + r);
		return result;

	}

}// end class
