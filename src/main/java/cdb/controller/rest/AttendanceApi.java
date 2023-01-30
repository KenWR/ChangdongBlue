package cdb.controller.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cdb.configuration.security.CdbUser;
import cdb.service.AttendanceSvc;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AttendanceApi {

	private final AttendanceSvc aSvc;

	@PatchMapping(value = "/att/off")
	public void getOffAtt(final Model mv, @AuthenticationPrincipal final CdbUser user) {
		aSvc.getOffAtt(mv, user);
	}

	@PostMapping(value = "/att/on")
	public void getOnAtt(final Model mv, @AuthenticationPrincipal final CdbUser user) {
		aSvc.getOnAtt(mv, user);
	}

	@PostMapping(value = "/mimi")
	public void mm() {
		System.out.println("컨트롤러 실행");
	}

}
