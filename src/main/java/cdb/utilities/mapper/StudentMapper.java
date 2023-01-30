package cdb.utilities.mapper;

import cdb.domain.dto.StudentListDTO;
import cdb.domain.entity.StudentEntity;
import cdb.utilities.generic.GenericMapper;

// @Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface StudentMapper extends GenericMapper<StudentListDTO, StudentEntity> {

}
