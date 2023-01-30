package cdb.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import cdb.domain.dto.CourseInsertDTO;
import cdb.domain.dto.CourseListDTO;
import cdb.domain.dto.StudentListDTO;
import cdb.domain.entity.CourseEntity;
import cdb.domain.entity.StudentCourseEntity;
import cdb.domain.repository.CourseRepository;
import cdb.domain.repository.StudentCourseRepository;
import cdb.domain.repository.StudentRepository;
import cdb.domain.repository.TeacherRepository;
import cdb.service.CourseSvc;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseSvcProc implements CourseSvc {

	private final CourseRepository cr;

	private final StudentCourseRepository scr;

	private final StudentRepository sr;

	private final TeacherRepository tr;

	@Override
	public void findCourse(final Model model) {
		// 코스를 전부 가져옵니다. - 선생님이나 학생이 비어있으면 오류가 뜨니까 이때는 no에 해당하는 title만 가져옵니다.
		// 이때 선생님도 같이 조회하고 싶어요
		// input type select로 할 예정입니다.
		// return cList.stream().map(CourseListDTO::new).collect(Collectors.toList());
		final List<CourseListDTO> courseList = cr.findAll().stream().map(this::toDTO).collect(Collectors.toList());
		model.addAttribute("course", courseList);
	}

	@Override
	public void findStudentsInCourse(final long no, final Model model) {
		final var studentsList = scr.findByCourse(no).stream().map(this::toDTO).collect(Collectors.toList());
		model.addAttribute("course", no);
		model.addAttribute("students", studentsList);
	}

	@Override
	public void regStudentAtt(final long cNo, final long sNo) {
	}

	@Override
	public void save(final CourseInsertDTO dto) {
		cr.save(toEntity(dto));
	}

	private CourseListDTO toDTO(final CourseEntity arg0) {
		if (arg0 == null) return null;

		final var courseListDTO = new CourseListDTO();

		courseListDTO.setEndDate(arg0.getEndDate());
		courseListDTO.setMaxSt(arg0.getMaxSt());
		courseListDTO.setMinSt(arg0.getMinSt());
		courseListDTO.setNo(arg0.getNo());
		courseListDTO.setPrice(arg0.getPrice());
		courseListDTO.setRoomNum(arg0.getRoomNum());
		courseListDTO.setStartDate(arg0.getStartDate());
		courseListDTO.setTitle(arg0.getTitle());
		courseListDTO.setTeacherNo(arg0.getTeacher().getNo());

		return courseListDTO;
	}

	private StudentListDTO toDTO(final StudentCourseEntity arg0) {
		if (arg0 == null) return null;

		final var studentListDTO = new StudentListDTO();

		studentListDTO.setAddr(arg0.getStudent().getAddr());
		studentListDTO.setName(arg0.getStudent().getName());
		studentListDTO.setNo(arg0.getStudent().getNo());
		studentListDTO.setPhone(arg0.getStudent().getPhone());

		return studentListDTO;
	}

	private CourseEntity toEntity(final CourseInsertDTO arg0) {
		if (arg0 == null) return null;

		final var courseEntity = CourseEntity.builder();

		courseEntity.endDate(arg0.getEndDate());
		courseEntity.maxSt(arg0.getMaxSt());
		courseEntity.minSt(arg0.getMinSt());
		courseEntity.price(arg0.getPrice());
		courseEntity.roomNum(arg0.getRoomNum());
		courseEntity.startDate(arg0.getStartDate());
		courseEntity.title(arg0.getTitle());
		courseEntity.teacher(tr.findById(arg0.getTeacherNo()).orElseThrow());

		return courseEntity.build();
	}

}