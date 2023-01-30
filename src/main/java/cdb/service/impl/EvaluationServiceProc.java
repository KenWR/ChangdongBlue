package cdb.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import cdb.domain.dto.EmployeeEvaluationInsertDTO;
import cdb.domain.dto.EvaluationListDTO;
import cdb.domain.entity.EvaluationEntity;
import cdb.domain.repository.EmployeeRepository;
import cdb.domain.repository.EvaluationRepository;
import cdb.service.EvaluationSvc;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EvaluationServiceProc implements EvaluationSvc {

	private final EvaluationRepository repo;

	private final EmployeeRepository er;

	@Override
	public void result(final Model model) {

		final var list = repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
		model.addAttribute("evalList", list);
	}

	@Override
	public void save(final EmployeeEvaluationInsertDTO dto) {
		repo.save(toEntity(dto));

	}

	private EvaluationListDTO toDTO(final EvaluationEntity arg0) {
		if (arg0 == null) return null;

		final var evaluationListDTO = new EvaluationListDTO();
		evaluationListDTO.setEmpNo(arg0.getEmployee().getNo());
		evaluationListDTO.setQuestion(arg0.getQuestion());
		evaluationListDTO.setScore(arg0.getScore());

		return evaluationListDTO;
	}

	private EvaluationEntity toEntity(final EmployeeEvaluationInsertDTO arg0) {
		if (arg0 == null) return null;

		final var evaluationEntity = EvaluationEntity.builder();

		evaluationEntity.employee(er.findById(arg0.getEmpNo()).orElseThrow());
		evaluationEntity.question(arg0.getQuestion());
		evaluationEntity.score(arg0.getScore());

		return evaluationEntity.build();
	}

}
