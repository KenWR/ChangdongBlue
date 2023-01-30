package cdb.service.impl;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import cdb.domain.dto.TeacherListDTO;
import cdb.domain.dto.reg.TeacherRegDTO;
import cdb.domain.entity.TeacherEntity;
import cdb.domain.etc.enums.CdbRole;
import cdb.domain.repository.ImgFileRepository;
import cdb.domain.repository.TeacherRepository;
import cdb.service.TeacherSvc;
import cdb.utilities.FileUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherSvcProc implements TeacherSvc {

	private final TeacherRepository tr;

	private final PasswordEncoder pe;

	@Value("${multipart.upload.temp}")
	private String locationTemp;

	@Value("${multipart.upload.path}")
	private String locationUpload;

	private final ImgFileRepository imgRepo;

	@Override
	public Map<String, String> fileTempUpload(final MultipartFile gImg) {
		return FileUtils.fileUpload(gImg, locationUpload);
	}

	@Override
	public void findAll(final Model model) {
		final var result = tr.findAll().stream().map(this::toDTO).collect(Collectors.toList());
		model.addAttribute("list", result);
	}

	// 파일 삽입시 문제 발생
	@Override
	public void reg(final TeacherRegDTO dto) {
		final var teacher = tr.save(TeacherEntity	.builder()
																							.name(dto.getName())
																							.email(dto.getEmail())
																							.phone(dto.getPhone())
																							.addr(dto.getAddr())
																							.dateOfBirth(dto.getDateOfBirth())
																							.password(pe.encode(dto.getPassword()))
																							.gender(dto.isGender())
																							.build())
													.addRole(CdbRole.TEACHER);

		dto.toImgFile(teacher, locationUpload).forEach(imgRepo::save);

	}

	private TeacherListDTO toDTO(final TeacherEntity arg0) {
		if (arg0 == null) return null;

		final var teacherListDTO = new TeacherListDTO();

		if (arg0.getAddr() != null) teacherListDTO.setAddr(arg0.getAddr());
		if (arg0.getEmail() != null) teacherListDTO.setEmail(arg0.getEmail());
		if (arg0.getName() != null) teacherListDTO.setName(arg0.getName());
		teacherListDTO.setNo(arg0.getNo());
		if (arg0.getPhone() != null) teacherListDTO.setPhone(arg0.getPhone());
		final var set = arg0.getRoles();
		if (set != null) teacherListDTO.setRoles(new LinkedHashSet<>(set));

		return teacherListDTO;
	}

}
