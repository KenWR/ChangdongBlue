package cdb.utilities.mapper;

import cdb.domain.dto.TeacherBoardDTO;
import cdb.domain.entity.TeacherEntity;
import cdb.utilities.generic.GenericMapper;

// @Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TeacherMapper extends GenericMapper<TeacherBoardDTO, TeacherEntity> {

}