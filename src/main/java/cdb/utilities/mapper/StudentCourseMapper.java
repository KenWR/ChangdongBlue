package cdb.utilities.mapper;

import cdb.domain.dto.StudentCourseInsertDTO;
import cdb.domain.entity.StudentCourseEntity;
import cdb.utilities.generic.GenericMapper;

// @Mapper(uses = { CourseMapper.class, StudentMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StudentCourseMapper extends GenericMapper<StudentCourseInsertDTO, StudentCourseEntity> {

}
