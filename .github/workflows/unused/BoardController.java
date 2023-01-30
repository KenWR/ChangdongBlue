package cdb.controller.basic;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cdb.service.EmployeeBoardSvc;
import cdb.service.TeacherBoardSvc;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final TeacherBoardSvc tbSvc;

	private final EmployeeBoardSvc ebSvc;

	// 직원 게시판 페이지로 이동합니다.
	@GetMapping("/board/employee")
	public void goEmployeeBoard(final Model model, @PageableDefault(size = 10) final Pageable pageable) {
		model.addAttribute("eBoard", ebSvc.getEmployeeBoardList(pageable));
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		model.addAttribute("next", pageable.next().getPageNumber());
	}

	@GetMapping("/board/employee-detail")
	public String goEmployeeBoardDetail(final long no, final long employeeNo, final Model model) {
		model.addAttribute("eBoard", ebSvc.getEmployeeBoardDetail(no));
		return "detail";
	}

	// 선생님 게시판 페이지로 이동합니다.
	@GetMapping("/board/teacher")
	public void goTeacherBoard(final Model model, @PageableDefault(size = 10) final Pageable pageable) {
		// model.addAttribute("tBoard", tbSvc.getTeacherBoardList(pageable));
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		model.addAttribute("next", pageable.next().getPageNumber());
	}

	@GetMapping("/board/teacher-detail")
	public String goTeacherBoardDetail(final long no, final long teacherNo, final Model model) {
		// model.addAttribute("tBoard", tbSvc.getTeacherBoardDetail(no));
		return "detail";
	}

}
