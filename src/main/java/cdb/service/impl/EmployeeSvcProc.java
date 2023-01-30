package cdb.service.impl;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import cdb.domain.dto.EmployeeListDTO;
import cdb.domain.dto.reg.EmployeeInsertDTO;
import cdb.domain.entity.EmployeeEntity;
import cdb.domain.etc.enums.CdbRole;
import cdb.domain.repository.EmployeeRepository;
import cdb.domain.repository.EvaluationRepository;
import cdb.domain.repository.ImgFileRepository;
import cdb.service.EmployeeSvc;
import cdb.utilities.FileUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeSvcProc implements EmployeeSvc {

	@Value("${multipart.upload.temp}")
	private String locationTemp;

	@Value("${multipart.upload.path}")
	private String locationUpload;

	private final PasswordEncoder pe;

	private final EmployeeRepository er;

	private final EvaluationRepository evr;

	private final ImgFileRepository imgRepo;

	@Override
	public Map<String, String> fileTempUpload(final MultipartFile gImg) {
		return FileUtils.fileUpload(gImg, locationUpload);
	}

	@Override
	public void findAll(final Model model) {
		final var list = er.findAll().stream().map(this::toDTO).collect(Collectors.toList());
		model.addAttribute("empList", list);
	}

	// 파일 업로드 문제 발생
	@Override
	public void insertEmp(@Valid final EmployeeInsertDTO dto) {
		final var employee = er.save(EmployeeEntity	.builder()
																								.name(dto.getName())
																								.email(dto.getEmail())
																								.addr(dto.getAddr())
																								.phone(dto.getPhone())
																								.dateOfBirth(dto.getDateOfBirth())
																								.password(pe.encode(dto.getPassword()))
																								.gender(dto.isGender())
																								.build())
														.addRole(CdbRole.HR);
		dto.toImgFile(employee, locationUpload).forEach(imgRepo::save);
	}

	private EmployeeListDTO toDTO(final EmployeeEntity arg0) {
		if (arg0 == null) return null;

		final var employeeListDTO = new EmployeeListDTO();

		employeeListDTO.setAddr(arg0.getAddr());
		employeeListDTO.setEmail(arg0.getEmail());
		employeeListDTO.setName(arg0.getName());
		employeeListDTO.setNo(arg0.getNo());
		employeeListDTO.setPhone(arg0.getPhone());
		final var set = arg0.getRoles();
		if (set != null) employeeListDTO.setRoles(new LinkedHashSet<>(set));

		return employeeListDTO;
	}

}
