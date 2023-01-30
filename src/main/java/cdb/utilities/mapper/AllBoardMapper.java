package cdb.utilities.mapper;

import cdb.domain.dto.TeacherBoardDTO;
import cdb.domain.entity.BoardEntity;
import cdb.utilities.generic.GenericMapper;

// @Mapper(uses = { EmployeeMapper.class, TeacherMapper.class }, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AllBoardMapper extends GenericMapper<TeacherBoardDTO, BoardEntity> {

}
