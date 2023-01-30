package cdb.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cdb.domain.dto.StudentCourseInsertDTO;
import cdb.domain.entity.StudentCourseEntity;
import cdb.domain.repository.CourseRepository;
import cdb.domain.repository.StudentCourseRepository;
import cdb.domain.repository.StudentRepository;
import cdb.service.StudentCourseSvc;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentCourseSvcProc implements StudentCourseSvc {

	private final StudentCourseRepository scRepo;

	private final CourseRepository cr;

	private final StudentRepository sr;

	@Override
	@Transactional
	public void save(final StudentCourseInsertDTO arg0, final StudentCourseEntity studentCourseEntity) {// 학생, 강의 pk를
		// StudentCourseEntity에
		// 담아내는 서비스
		final var sc = scRepo.findByStudentNo(arg0.getSno());
		System.out.println("sc>>>" + sc + arg0.getSno());
		sc.update(sr.findById(arg0.getSno()).get(), cr.findById(arg0.getCno()).get());
	}

	// private StudentCourseEntity toEntity(final StudentCourseInsertDTO arg0) {
	// if (arg0 == null) return null;
	//
	// final var studentCourseEntity = StudentCourseEntity.builder();
	// studentCourseEntity.course(cr.findById(arg0.getCno()).orElseThrow());
	// studentCourseEntity.student(sr.findById(arg0.getSno()).orElseThrow());
	// return studentCourseEntity.build(); - 0126 시현 주석처리
	// }

}
