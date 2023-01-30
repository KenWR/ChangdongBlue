package cdb.controller.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cdb.domain.dto.reg.TeacherRegDTO;
import cdb.service.TeacherSvc;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TeacherController {

	private final TeacherSvc tSvc;

	@GetMapping("/teacher/list")
	public String teacherList(final Model model) {
		tSvc.findAll(model);
		return "teacher/teacher-list";
	}

	@GetMapping("/teacher/reg")
	public String teacherReg() {
		return "teacher/teacher-reg";
	}

	// rawPassword cannot be null
	@PostMapping("/teacher/register")
	public String teacherReg(final TeacherRegDTO dto) {
		tSvc.reg(dto);
		return "teacher/teacher-reg";
	}

}
