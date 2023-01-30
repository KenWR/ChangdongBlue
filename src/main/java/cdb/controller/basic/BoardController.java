package cdb.controller.basic;

import org.springframework.stereotype.Controller;

import cdb.service.BoardSvc;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardSvc bSvc;

	// @GetMapping("/board")
	// public void goTeacherBoard(final Model model, final BoardCategory boardCategory,
	// @PageableDefault(size = 10) final Pageable pageable) {
	// bSvc.getBoardList(boardCategory, model, pageable);
	// }

	// @GetMapping("/board/detail")
	// public void goTeacherBoardDetail(final BoardDTO dto, final Model model) {
	// bSvc.getBoardDetail(dto, model);
	// }

}