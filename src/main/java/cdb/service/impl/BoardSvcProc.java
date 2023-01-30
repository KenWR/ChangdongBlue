package cdb.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import cdb.domain.dto.BoardDTO;
import cdb.domain.dto.BoardListDTO;
import cdb.domain.entity.BoardEntity;
import cdb.domain.etc.enums.BoardCategory;
import cdb.domain.repository.BoardRepository;
import cdb.service.BoardSvc;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardSvcProc implements BoardSvc {

	private final BoardRepository br;

	@Override
	public void getBoardDetail(final BoardDTO dto, final Model model) {
		final var entity = toDetailDTO(br.findById(dto.getBoardNo()).orElseThrow());
		model.addAttribute("board", entity);
	}

	@Override
	public void getBoardList(final BoardCategory boardCategory, final Model model, final Pageable pageable) {

		final var list = br	.findAllByBoardCategory(boardCategory, pageable)
												.stream()
												.map(this::toListDTO)
												.collect(Collectors.toList());
		model.addAttribute("boardList", list);
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		model.addAttribute("next", pageable.next().getPageNumber());
	}

	private BoardListDTO toListDTO(final BoardEntity arg0) {
		if (arg0 == null) return null;

		final var boardListDTO = new BoardListDTO();

		boardListDTO.setBoardCategory(arg0.getBoardCategory());
		boardListDTO.setTitle(arg0.getTitle());
		boardListDTO.setUpdatedDate(arg0.getUpdatedDate());
		boardListDTO.setBoardNo(arg0.getNo());

		if (arg0.getEmployee() != null) {
			boardListDTO.setWriter(arg0.getEmployee().getEmail());
			boardListDTO.setWriterNo(arg0.getEmployee().getNo());
		} else {
			boardListDTO.setWriter(arg0.getTeacher().getEmail());
			boardListDTO.setWriterNo(arg0.getTeacher().getNo());
		}
		return boardListDTO;
	}

	private BoardDTO toDetailDTO(final BoardEntity arg0) {
		if (arg0 == null) return null;

		final var boardListDTO = new BoardDTO();

		boardListDTO.setBoardCategory(arg0.getBoardCategory());
		boardListDTO.setTitle(arg0.getTitle());
		boardListDTO.setUpdatedDate(arg0.getUpdatedDate());
		boardListDTO.setBoardNo(arg0.getNo());

		if (arg0.getEmployee() != null) {
			boardListDTO.setWriter(arg0.getEmployee().getEmail());
			boardListDTO.setWriterNo(arg0.getEmployee().getNo());
		} else {
			boardListDTO.setWriter(arg0.getTeacher().getEmail());
			boardListDTO.setWriterNo(arg0.getTeacher().getNo());
		}
		return boardListDTO;
	}

}
