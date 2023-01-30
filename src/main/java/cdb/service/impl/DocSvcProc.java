package cdb.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cdb.configuration.security.CdbUser;
import cdb.domain.dto.DocInsertDTO;
import cdb.domain.dto.DocListDTO;
import cdb.domain.dto.DocUpdateDTO;
import cdb.domain.entity.DocEntity;
import cdb.domain.etc.enums.DocStatus;
import cdb.domain.etc.enums.DocType;
import cdb.domain.repository.DocRepository;
import cdb.domain.repository.EmployeeRepository;
import cdb.domain.repository.TeacherRepository;
import cdb.service.DocSvc;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocSvcProc implements DocSvc {

	private final DocRepository docRepository;

	private final EmployeeRepository er;

	private final TeacherRepository tr;

	@Override
	public void docReg(final Model model) {

	}

	@Override
	public void findAll(final Model model) {
		final var list = docRepository.findAll();
		final List<DocListDTO> dList = list.stream().map(this::toDTO).collect(Collectors.toList());
		model.addAttribute("doc", dList);
	}

	@Override
	public void findById(final long no, final Model model) {
		model.addAttribute("detail", docRepository.findById(no).map(this::toDTO).orElseThrow());
		model.addAttribute("docNo", no);
	}

	@Override
	public void findByStatus(final Model model, final DocStatus docStatus) {
		// if(dto.getDocStatus().DEFAULT==DocStatus.DEFAULT) {

		final var list = docRepository.findByDocStatus(docStatus);
		final List<DocListDTO> dList = list.stream().map(this::toDTO).collect(Collectors.toList());
		model.addAttribute("doc", dList);
	}

	// status.default문서 조회
	@Override
	public void findByStatusDefault(final Model model) {
		final var list = docRepository.findByDocStatus(DocStatus.DEFAULT);
		final List<DocListDTO> dList = list.stream().map(this::toDTO).collect(Collectors.toList());
		model.addAttribute("doc", dList);
	}

	// 0120 - 승인된 결근+휴가 문서를 가져옵니다.
	// Optional로 하면 왜 query did not return a unique result: 2 뜨는지 찾아볼것
	@Override
	public void getStatApprovalAndTypeAbsenceOrVacation(final Model model) {
		model.addAttribute("doc",
				docRepository	.findByDocStatusAndDocTypeOrDocStatusAndDocType(DocStatus.APPROVAL, DocType.VACATION,
													DocStatus.APPROVAL, DocType.ABSENCE)// 결근은 승인 아니어도
											.stream()
											.map(this::toDTO)
											.collect(Collectors.toList()));
	}

	// 0119 시현 - 시간 저장 추가 이걸로 덮어써주세요
	// role 이 teacher 이면 doc테이블 teacher_no로 저장
	// 다른 role이면 employee_no로 저장
	@Override
	public void saveDoc(final DocInsertDTO dto, final CdbUser user) {
		final var emp = DocEntity	.builder()
															.content(dto.getContent())
															.docType(dto.getDocType())
															.docStatus(DocStatus.DEFAULT)
															.title(dto.getTitle())
															.startDate(ifDateNull(dto.getStartDate()))
															.endDate(ifDateNull(dto.getEndDate()))
															.employee(er.findById(user.getNo()).orElseThrow())
															.build();
		final var tea = DocEntity	.builder()
															.content(dto.getContent())
															.docType(dto.getDocType())
															.docStatus(DocStatus.DEFAULT)
															.title(dto.getTitle())
															.startDate(ifDateNull(dto.getStartDate()))
															.endDate(ifDateNull(dto.getEndDate()))
															.teacher(tr.findById(user.getNo()).orElseThrow())
															.build();
		if (user.isEmployee())
			docRepository.save(emp);
		else docRepository.save(tea);
	}

	private LocalDate ifDateNull(final LocalDate date) {
		return date;
	}

	private DocListDTO toDTO(final DocEntity arg0) {
		if (arg0 == null) return null;

		final var docListDTO = new DocListDTO();

		docListDTO.setContent(arg0.getContent());
		docListDTO.setDocStatus(arg0.getDocStatus());
		docListDTO.setDocType(arg0.getDocType());
		docListDTO.setEndDate(ifDateNull(arg0.getEndDate()));
		docListDTO.setNo(arg0.getNo());
		docListDTO.setStartDate(ifDateNull(arg0.getStartDate()));
		docListDTO.setTitle(arg0.getTitle());
		final var email = arg0.getEmployee() != null ? arg0.getEmployee().getEmail() : arg0.getTeacher().getEmail();
		docListDTO.setEmail(email);
		final var name = arg0.getEmployee() != null ? arg0.getEmployee().getName() : arg0.getTeacher().getName();
		docListDTO.setName(name);
		return docListDTO;

	}

	// 문서 업데이트
	@Transactional // pk조회한후 ---map 엔티티를 수정: 수정된정보 update가 자동으로실행될려면 session이 유지되어야함
	@Override
	public void update(final DocUpdateDTO dto) {
		docRepository.findById(dto.getDocNo()).map(e -> e.update(dto.getDocStatus()));
		// System.out.println(dto.getNo());

	}
	// @Transactional
	// @Override
	// public void update(long no, DocStatus docStatus) {
	// docRepository.findById(no).get().setDocStatus(docStatus);
	// }

}
