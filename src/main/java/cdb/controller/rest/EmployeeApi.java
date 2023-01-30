package cdb.controller.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cdb.service.EmployeeSvc;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeApi {

	private final EmployeeSvc eSvc;

	@PostMapping(value = "/emp/temp-upload")
	public Map<String, String> FileUpload(final MultipartFile gImg) {
		return eSvc.fileTempUpload(gImg);
	}

}
