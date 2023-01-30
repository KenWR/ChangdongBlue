package cdb.controller.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cdb.service.TeacherSvc;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TeacherApi {

	private final TeacherSvc tSvc;

	@PostMapping("/teacher/temp-upload")
	public Map<String, String> FileUpload(final MultipartFile gImg) {
		return tSvc.fileTempUpload(gImg);
	}

}
