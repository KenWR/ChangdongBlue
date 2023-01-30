package cdb.controller.rest;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cdb.domain.dto.reg.StudentInsertDTO;
import cdb.service.StudentSvc;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentApi {

	private final StudentSvc sSvc;

	@PostMapping("/student/temp-upload")
	public Map<String, String> FileUpload(final MultipartFile gImg) {
		return sSvc.fileTempUpload(gImg);
	}

	@PostMapping(value = "/student/reg")
	public ResponseEntity<HttpStatus> postMethodName(@Valid final StudentInsertDTO dto) {
		sSvc.reg(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
