package cdb.controller.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cdb.domain.dto.EmployeeEvaluationInsertDTO;
import cdb.service.EvaluationSvc;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EvaluationController {

	private final EvaluationSvc evSvc;

	// 평가조회
	@GetMapping("/emp/result")
	public String result(final Model model) {
		evSvc.result(model);
		return "employee/employee-evaluation";
	}

	// 평가등록
	@PostMapping("/emp/evaluation")
	public String save(final EmployeeEvaluationInsertDTO dto) {
		evSvc.save(dto);
		return "employee/employee-evaluation";
	}

}
