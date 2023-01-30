package cdb.service;

import org.springframework.ui.Model;

import cdb.domain.dto.CourseInsertDTO;

public interface CourseSvc {

	// void findStudentsInCourse(long no, Model model);

	void findCourse(Model model);

	void findStudentsInCourse(long no, Model model);

	void regStudentAtt(long cNo, long sNo);

	void save(CourseInsertDTO dto);

}
