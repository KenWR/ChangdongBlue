package cdb.service;

import org.springframework.ui.Model;

import cdb.configuration.security.CdbUser;
import cdb.domain.dto.DocInsertDTO;
import cdb.domain.dto.DocUpdateDTO;
import cdb.domain.etc.enums.DocStatus;

public interface DocSvc {

	void docReg(Model model);

	void findAll(Model model);

	void findById(long no, Model model);

	void findByStatus(Model model, DocStatus docStatus);

	void findByStatusDefault(Model model);

	void getStatApprovalAndTypeAbsenceOrVacation(Model model);

	// void detail(long no, Model model);

	void saveDoc(DocInsertDTO dto, CdbUser user);

	void update(DocUpdateDTO dto);

}
