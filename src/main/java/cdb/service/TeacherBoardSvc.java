package cdb.service;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

public interface TeacherBoardSvc {

	void getTeacherBoardDetail(long no, Model model);

	void getTeacherBoardList(Pageable pageable, final Model model);

}
