package cdb.controller.basic;

import java.time.LocalDate;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cdb.configuration.security.CdbUser;
import cdb.domain.dto.DocInsertDTO;
import cdb.domain.etc.enums.DocStatus;
import cdb.service.DocSvc;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DocController {

	private final DocSvc dSvc;

	// status.default문서 조회
	@GetMapping("/docDetail")
	public String docDetail(final Model model) {
		dSvc.findByStatusDefault(model);
		return "document/detail";

	}

	@PostMapping("/doc/reg")
	public String docReg(final DocInsertDTO dto, @AuthenticationPrincipal final CdbUser user) {
		dSvc.saveDoc(dto, user);
		return "redirect:/doc/reg";
	}

	// 문서 작성 페이지로 넘어갑니다.
	@GetMapping("/doc/reg")
	public String docReg(final Model model) {

		// dSvc.docReg(model);
		// 작성자 정보
		// model.addAttribute("userNo", user.getNo());
		// System.out.println("결과는 " + user.getNo());
		// model.addAttribute("userName", user.getName());
		// model.addAttribute("roles", user.getRoles());

		// 문서 종류 지정
		// model.addAttribute("docTypeCourse", DocType.COURSE);
		// model.addAttribute("docTypeItem", DocType.ITEM);
		// model.addAttribute("docTypeVacation", DocType.VACATION);

		// 작성 날짜를 표시하기위한 더미데이터 실제로는 사용하지 않습니다.
		model.addAttribute("localDate", LocalDate.now());
		return "doc/reg";
	}

	@GetMapping("/docUpdate")
	public String docUpdate() {
		// dSvc.update(no, model);
		return "document/detail";
	}

	// 모든 결재문서 찾아오기(o)
	@GetMapping("/docList")
	public String getDocList(final Model model) {
		dSvc.findAll(model);
		return "document/list";
	}

	// 타입에 따라 분류하기 (o)
	@PostMapping("/docList")
	public String getDocList(final Model model, @RequestParam final DocStatus docStatus) {
		dSvc.findByStatus(model, docStatus);
		return "document/list";
	}

}
