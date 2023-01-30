package cdb.service;

import org.springframework.ui.Model;

import cdb.domain.dto.EmployeeEvaluationInsertDTO;

public interface EvaluationSvc {

	void result(Model model);

	void save(EmployeeEvaluationInsertDTO dto);

}
