package cdb.controller.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import cdb.service.CourseSvc;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TestController {

	private final CourseSvc cSvc;

	@GetMapping(value = "/dd")
	public String goSomeswhere() {
		return "/board/notice";
	}

	@GetMapping(value = "/d")
	public String goSomewhere() {
		return "/employee/reg/insertMember";
	}

	@GetMapping(value = "/ddd")
	public String goSomewhsere() {
		return "/teacher/attendance";
	}

	@GetMapping(value = "/dddd")
	public String goSomewhserae() {
		return "/employee/list";
	}

	/*
	 * @GetMapping(value = "/") public String goToIndex(Model model) { //현재 개강중인 강좌 표시 cSvc.findCourse(model); return
	 * "index"; }
	 */

}
