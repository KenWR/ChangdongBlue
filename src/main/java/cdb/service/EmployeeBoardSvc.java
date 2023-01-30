package cdb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import cdb.domain.dto.EmployeeBoardDTO;

public interface EmployeeBoardSvc {

	EmployeeBoardDTO getEmployeeBoardDetail(long no);

	List<EmployeeBoardDTO> getEmployeeBoardList(Pageable pageable);

}
