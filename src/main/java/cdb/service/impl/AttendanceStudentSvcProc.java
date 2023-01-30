package cdb.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cdb.domain.dto.StudentAttListDTO;
import cdb.domain.dto.StudentListDTO;
import cdb.domain.entity.AttendanceStudentEntity;
import cdb.domain.entity.StudentCourseEntity;
import cdb.domain.repository.AttendanceStudentRepository;
import cdb.domain.repository.CourseRepository;
import cdb.domain.repository.StudentCourseRepository;
import cdb.domain.repository.StudentRepository;
import cdb.service.AttendanceStudentSvc;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceStudentSvcProc implements AttendanceStudentSvc {

	private final AttendanceStudentRepository asr;

	private final StudentRepository sr;

	private final CourseRepository cr;

	private final StudentCourseRepository scr;

	@Override
	@Transactional
	public void findStuByCourse(final Model model, final long courseNo) {
		final var result = scr.findByCourseNo(courseNo);
		final List<StudentListDTO> student = result.stream().map(this::toDTOgetStudent).collect(Collectors.toList());
		// model.addAttribute("sad", student);

		final List<Long> numbers = new ArrayList<>();

		for (var i = 0; i < student.size(); i++) {
			final var no = result.get(i).getStudent().getNo();
			// System.out.println(no);
			numbers.add(no);
		}

		final var result2 = asr.findByDayAndStudentNoIn(LocalDate.now(), numbers);
		final List<StudentAttListDTO> att = result2.stream().map(this::toDTOgetStatus).collect(Collectors.toList());
		model.addAttribute("att", att);
	}

	private StudentListDTO toDTOgetStudent(final StudentCourseEntity arg0) {
		if (arg0 == null) return null;

		final var studentListDTO = new StudentListDTO();

		studentListDTO.setNo(arg0.getStudent().getNo());
		return studentListDTO;
	}

	private StudentAttListDTO toDTOgetStatus(final AttendanceStudentEntity arg0) {
		if (arg0 == null) return null;
		final var studentAttListDTO = new StudentAttListDTO();
		studentAttListDTO.setNo(arg0.getStudent().getNo());
		studentAttListDTO.setStatus(arg0.getStatus().getKorean());
		studentAttListDTO.setName(arg0.getStudent().getName());
		studentAttListDTO.setDay(arg0.getDay());
		studentAttListDTO.setOnTime(arg0.getOnTime());
		studentAttListDTO.setOffTime(arg0.getOffTime());
		return studentAttListDTO;

	}

	// @Override
	// public void findAllStuAtt(final Model model) {
	// final var list = this.sr.findAll();

	// model.addAttribute("list", list);
	// }

	// @Transactional
	// public void name() {

	// // 학생인데 코스에 등록되어있고 그 코스는 시작일과 오늘 날짜보다 after
	// this.cr.findAllByStartDateBetween(null, null);
	// this.sr.findAll()
	// .forEach(
	// s ->
	// this.asr.save(AttendanceStudentEntity.builder().day(LocalDate.now()).student(s).status(null).build()));
	// }

	// @Transactional
	// public void name2(final LocalDate day, final long no) {
	// this.asr.findByDayAndStudentNo(day,
	// no).orElseThrow().setOnTime(LocalTime.now()).setStatus(this.name3());
	// final var att = this.asr.findByDayAndStudentNo(day, no).orElseThrow();
	// att.setOnTime(LocalTime.now());
	// if (LocalTime.now().isBefore(AttendanceTime.ST_ON_TIME.strTime()))
	// att.setStatus(AttendanceStudentStatus.ON);
	// att.setStatus(AttendanceStudentStatus.LATE);
	// }

	// @Transactional
	// public void name3(final LocalDate day, final long no) {
	// // this.asr.findByDayAndStudentNo(day,
	// // no).orElseThrow().setOnTime(LocalTime.now()).setStatus(this.name3());
	// final var att = this.asr.findByDayAndStudentNo(day, no).orElseThrow();
	// att.setOffTime(LocalTime.now());
	// if (LocalTime.now().isAfter(AttendanceTime.ST_OFF_TIME.strTime()))
	// att.setStatus(AttendanceStudentStatus.OFF);
	// att.setStatus(AttendanceStudentStatus.EARLY);
	// att.setStatus(AttendanceStudentStatus.SICK);
	// }

	// private AttendanceStudentStatus name3() {

	// if (LocalTime.now().isBefore(AttendanceTime.ST_ON_TIME.strTime())) return
	// AttendanceStudentStatus.EARLY;
	// return AttendanceStudentStatus.LATE;
	// }

	/*
	 * @Override public void findTime(Model model) { final var list = ar.findAll(); final var time = list.stream()
	 * .map(StudentAttDTO::new) .collect(Collectors.toList()); model.addAttribute("time", time); }
	 * @Override public void saveAtt(final StudentCourseInsertDTO sciDTO) { ar.save(sciDTO.toASEntity()); }
	 * @Transactional
	 * @Override public void saveDayAndStatus(StudentAttInsertDTO dto, Long no) { final Optional<AttendanceStudentEntity>
	 * sad = ar.findById(no); // ar.save(dto.toEntity()); }
	 * @Override public void findStuByCourse(Model model, long courseNo) { List<StudentAttDTO> ase
	 * =ar.findAllByCourse_no(courseNo).stream() .map(StudentAttDTO::new).collect(Collectors.toList());
	 * //List<StudentAttDTO> sad = ase.stream().map(StudentAttDTO::new).collect(Collectors.toList());
	 * model.addAttribute("sad", ase); }
	 */
}