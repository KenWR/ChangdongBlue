package cdb.controller.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cdb.domain.dto.reg.StudentInsertDTO;
import cdb.service.AttendanceStudentSvc;
import cdb.service.CourseSvc;
import cdb.service.StudentSvc;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentController {

	private final StudentSvc sSvc;

	private final AttendanceStudentSvc asSvc;

	private final CourseSvc cSvc;

	// 모든 학생 출력
	@GetMapping("/stu")
	public String allStudent(final Model model) {
		sSvc.findAll(model);
		return "students/students-list";
	}

	// 확인 필요
	@GetMapping("/check/att/{courseNo}")
	public String checkAtt(final Model model, @PathVariable final long courseNo) {
		asSvc.findStuByCourse(model, courseNo);
		return "students/attendance-time";
	}

	// // 분류 조회를 위한 데이터 받아오기
	// @PostMapping("/check/att")
	// public String forSort() {
	//
	// return "#";
	// }

	@GetMapping("/students-info")
	public String studentInfo() {
		return "students/students-info";
	}

	@PostMapping("/fs")
	public String studentInfo(final StudentInsertDTO dto) {
		sSvc.reg(dto);
		return "students/students-info";
	}

	@GetMapping("/student/reg")
	public String studentReg(final Model model) {
		sSvc.findAll(model);
		return "/students/reg";
	}

}
