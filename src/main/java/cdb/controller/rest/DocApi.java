package cdb.controller.rest;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cdb.domain.dto.DocUpdateDTO;
import cdb.service.DocSvc;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DocApi {

	private final DocSvc dSvc;

	// docStatus 수정
	@ResponseBody
	@PutMapping("/docUpdate")
	public void update(final DocUpdateDTO dto) {
		dSvc.update(dto);
		System.out.println("dddddddddddddd");
	}

}
