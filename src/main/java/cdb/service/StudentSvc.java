package cdb.service;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import cdb.domain.dto.reg.StudentInsertDTO;

public interface StudentSvc {

	Map<String, String> fileTempUpload(MultipartFile gImg);

	void findAll(Model model);

	void findStudentWhoNonRegCourse(Model model);

	void reg(StudentInsertDTO dto);

}
