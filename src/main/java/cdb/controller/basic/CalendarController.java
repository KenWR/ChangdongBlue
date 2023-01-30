package cdb.controller.basic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cdb.service.CalendarSvc;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CalendarController {

	private final CalendarSvc cSvc;

	@GetMapping(value = "/calendar/calendar")
	public void calendar() {

	}

	// 직원 연차, 결근 처리 메소드
	@PostMapping(value = "/att/e-absence")
	public String regAbsenceAtt(final long no, final Model mv) {
		cSvc.regAbsenceAtt(no, mv);
		return "redirect:/emp/reg/absence";
	}

	// 캘린더에 연차, 결근 데이터를 넘겨줍니다.
	@ResponseBody
	@GetMapping(value = "/calendar/data")
	public List<Map<String, Object>> calendarDate(Model mv) {
		return cSvc.findAll();
	}

}
