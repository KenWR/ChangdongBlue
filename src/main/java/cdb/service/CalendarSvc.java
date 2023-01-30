package cdb.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

public interface CalendarSvc {

	void regAbsenceAtt(long no, Model mv);

	List<Map<String, Object>> findAll();

}
