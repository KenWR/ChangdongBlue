package cdb.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import cdb.domain.dto.reg.EmployeeInsertDTO;

public interface EmployeeSvc {

	Map<String, String> fileTempUpload(MultipartFile gImg);

	void findAll(Model model);

	void insertEmp(@Valid EmployeeInsertDTO dto);

}
