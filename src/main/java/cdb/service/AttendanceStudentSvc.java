package cdb.service;

import org.springframework.ui.Model;

public interface AttendanceStudentSvc {

	void findStuByCourse(Model model, long courseNo);

	// void findAllStuAtt(Model model);

	// void findTime(Model model);

	// void saveAtt(StudentCourseInsertDTO studentCourseInsertDTO);

	// void saveDayAndStatus(StudentAttInsertDTO dto, Long no);

}
