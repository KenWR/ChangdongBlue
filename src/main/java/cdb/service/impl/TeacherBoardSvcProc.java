package cdb.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import cdb.domain.dto.TeacherBoardDTO;
import cdb.domain.entity.BoardEntity;
import cdb.domain.etc.enums.BoardCategory;
import cdb.domain.repository.BoardRepository;
import cdb.service.TeacherBoardSvc;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherBoardSvcProc implements TeacherBoardSvc {

	private final BoardRepository boardRepository;

	@Override
	public void getTeacherBoardDetail(final long no, final Model model) {
		model.addAttribute("teaDetail", toDTO(boardRepository.findById(no).orElseThrow()));
	}

	@Override
	public void getTeacherBoardList(final Pageable pageable, final Model model) {
		final var list = boardRepository.findAllByBoardCategory(BoardCategory.TEACHER, pageable)
																		.stream()
																		.map(this::toDTO)
																		.collect(Collectors.toList());
		model.addAttribute("teaList", list);
	}

	private TeacherBoardDTO toDTO(final BoardEntity arg0) {
		if (arg0 == null) return null;

		final var teacherBoardDTO = new TeacherBoardDTO();

		if (arg0.getBoardCategory() != null) teacherBoardDTO.setBoardCategory(arg0.getBoardCategory());
		if (arg0.getTeacher() != null) teacherBoardDTO.setTeacherName(arg0.getTeacher().getName());
		if (arg0.getTeacher() != null) teacherBoardDTO.setTeacherNo(arg0.getTeacher().getNo());
		if (arg0.getContent() != null) teacherBoardDTO.setContent(arg0.getContent());
		if (arg0.getCreatedDate() != null) teacherBoardDTO.setCreatedDate(arg0.getCreatedDate());
		if (arg0.getNo() != 0) teacherBoardDTO.setNo(arg0.getNo());
		if (arg0.getTitle() != null) teacherBoardDTO.setTitle(arg0.getTitle());
		if (arg0.getUpdatedDate() != null) teacherBoardDTO.setUpdatedDate(arg0.getUpdatedDate());

		return teacherBoardDTO;
	}

}
