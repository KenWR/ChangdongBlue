package cdb.service;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import cdb.domain.dto.BoardDTO;
import cdb.domain.etc.enums.BoardCategory;

public interface BoardSvc {

	void getBoardDetail(BoardDTO dto, Model model);

	void getBoardList(BoardCategory boardCategory, Model model, Pageable pageable);

}
