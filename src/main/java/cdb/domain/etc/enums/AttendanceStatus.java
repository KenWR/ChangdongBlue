package cdb.domain.etc.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AttendanceStatus {

	ON("출석"), // 출근시간보다 이전에 출근합니다.
	OFF("퇴근"), // 퇴근기본값
	LATE("지각"), // 출근시간보다 이후에 출근했습니다.
	EARLY("조퇴"), // 근무시간의 50%미만 근무했습니다.
	ABSENCE("결석"), // 하루동안 출석 이력이 없습니다.(직원OR선생의 경우 결근 - 시현)
	VACATION("연차"); // 추가했습니다.

	private final String korean;

	public String korean() {
		return korean;
	}

}
