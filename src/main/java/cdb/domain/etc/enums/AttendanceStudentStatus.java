package cdb.domain.etc.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttendanceStudentStatus {

	ON("출석"),
	ABSENCE("결석"),
	LATE("지각"),
	EARLY("조퇴"),
	SICK("병결");

	private final String korean;

	public String korean() {
		return korean;
	}

}
