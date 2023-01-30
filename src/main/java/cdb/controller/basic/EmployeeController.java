package cdb.controller.basic;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cdb.domain.dto.reg.EmployeeInsertDTO;
import cdb.service.AttendanceSvc;
import cdb.service.DocSvc;
import cdb.service.EmployeeSvc;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeSvc eSvc;

	private final AttendanceSvc aSvc;

	private final DocSvc dSvc;

	@GetMapping(value = "/emp/reg")
	public String employeeInsertPage() {
		return "employee/reg/employee-reg";
	}

	// 직원 등록
	@PostMapping(value = "/emp/reg")
	public String employeeSave(@Valid final EmployeeInsertDTO dto) {
		eSvc.insertEmp(dto);
		return "employee/reg/employee-reg";
	}

	@GetMapping("/emp/findAll")
	public String findAll() {
		// eSvc.findAll();
		return null;
	}

	// 리스트 출력 페이지
	@GetMapping("/emp/list")
	public String findAll(final Model model) {
		eSvc.findAll(model);
		return "employee/list";
	}

	// 선생OR직원 결근+휴가 등록 페이지로 이동합니다.
	// 승인된 결근+휴가 문서를 가져옵니다.
	@GetMapping(value = "/emp/reg/absence")
	public String regAbsence(final Model model) {
		dSvc.getStatApprovalAndTypeAbsenceOrVacation(model);
		return "employee/reg/absence";
	}

	// 승인된 결근+휴가 문서의 결근+휴가 처리페이지
	@GetMapping(value = "/emp/reg/absence/detail")
	public String regAbsenceDetail(@RequestParam("no") final long no, final Model model) {
		dSvc.findById(no, model);
		return "employee/reg/absence-reg-detail";
	}

}
