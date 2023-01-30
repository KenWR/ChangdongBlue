package cdb.service;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import cdb.domain.dto.reg.TeacherRegDTO;

public interface TeacherSvc {

	Map<String, String> fileTempUpload(MultipartFile gImg);

	void findAll(Model model);

	void reg(TeacherRegDTO dto);

}
