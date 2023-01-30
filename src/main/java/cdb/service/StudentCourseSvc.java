package cdb.service;

import cdb.domain.dto.StudentCourseInsertDTO;
import cdb.domain.entity.StudentCourseEntity;

public interface StudentCourseSvc {

	void save(StudentCourseInsertDTO arg0, StudentCourseEntity studentCourseEntity);

}
