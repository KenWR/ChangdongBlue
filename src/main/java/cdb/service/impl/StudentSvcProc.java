package cdb.service.impl;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import cdb.domain.dto.StudentListDTO;
import cdb.domain.dto.reg.StudentInsertDTO;
import cdb.domain.entity.StudentCourseEntity;
import cdb.domain.entity.StudentEntity;
import cdb.domain.repository.ImgFileRepository;
import cdb.domain.repository.StudentCourseRepository;
import cdb.domain.repository.StudentRepository;
import cdb.service.StudentSvc;
import cdb.utilities.FileUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentSvcProc implements StudentSvc {

	private final StudentRepository sr;

	private final ImgFileRepository ifr;

	private final StudentCourseRepository scr;

	@Value("${multipart.upload.temp}")
	private String locationTemp;

	@Value("${multipart.upload.path}")
	private String locationUpload;

	@Override
	public Map<String, String> fileTempUpload(final MultipartFile gImg) {
		return FileUtils.fileUpload(gImg, locationUpload);
	}

	@Override
	public void findAll(final Model model) {
		final var list = sr.findAll().stream().map(this::toDTO).collect(Collectors.toList());
		model.addAttribute("stList", list);
	}

	@Override
	public void findStudentWhoNonRegCourse(final Model model) {

		final var list = scr.findByCourseNoIsNull().stream().map(this::toDTO).collect(Collectors.toList());
		model.addAttribute("list", list);
	}

	@Override
	public void reg(final StudentInsertDTO dto) {
		final var studentEntity = StudentEntity.builder();
		studentEntity.addr(dto.getAddr());
		studentEntity.dateOfBirth(dto.getDateOfBirth());
		studentEntity.email(dto.getEmail());
		studentEntity.gender(dto.isGender());
		studentEntity.name(dto.getName());
		studentEntity.phone(dto.getPhone());
		sr.save(studentEntity.build());
	}

	private StudentListDTO toDTO(final StudentCourseEntity arg0) {
		if (arg0 == null) return null;

		final var studentListDTO = new StudentListDTO();

		studentListDTO.setAddr(arg0.getStudent().getAddr());
		studentListDTO.setName(arg0.getStudent().getName());
		studentListDTO.setNo(arg0.getStudent().getNo());
		studentListDTO.setPhone(arg0.getStudent().getPhone());

		return studentListDTO;
	}

	private StudentListDTO toDTO(final StudentEntity arg0) {
		if (arg0 == null) return null;

		final var studentListDTO = new StudentListDTO();

		studentListDTO.setAddr(arg0.getAddr());
		studentListDTO.setName(arg0.getName());
		studentListDTO.setNo(arg0.getNo());
		studentListDTO.setPhone(arg0.getPhone());

		return studentListDTO;
	}

}
