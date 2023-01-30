package cdb.utilities.mapper;

import cdb.domain.dto.CourseListDTO;
import cdb.domain.entity.CourseEntity;
import cdb.utilities.generic.GenericMapper;

// @Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CourseMapper extends GenericMapper<CourseListDTO, CourseEntity> {

}
