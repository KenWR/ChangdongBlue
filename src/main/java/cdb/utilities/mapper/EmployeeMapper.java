package cdb.utilities.mapper;

import cdb.domain.dto.EmployeeBoardDTO;
import cdb.domain.entity.EmployeeEntity;
import cdb.utilities.generic.GenericMapper;

// @Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EmployeeMapper extends GenericMapper<EmployeeBoardDTO, EmployeeEntity> {

}
