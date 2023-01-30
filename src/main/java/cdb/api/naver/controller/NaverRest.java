package cdb.api.naver.controller;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cdb.api.common.service.OpenApiSvc;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NaverRest {

	private final OpenApiSvc svc;

	// @GetMapping(value = "/naver/oauth2")
	// public ResponseEntity<TokenDTO> naverApiGetToken(final String code, final String state, final Model model)
	// throws IOException {
	// svc.getTokenAsDTO(code, model);
	// // naver/org-unit
	// return ResponseEntity.status(HttpStatus.OK).build();
	// }
	@GetMapping(value = "/naver/oauth2")
	public String naverApiGetToken(final String code, final String state, final Model model) throws IOException {
		System.out.println(code);
		svc.getTokenAsDTO(code, model);
		return "naver/org-unit";
	}

}
