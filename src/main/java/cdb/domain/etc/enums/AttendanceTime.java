package cdb.domain.etc.enums;

import java.time.LocalTime;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AttendanceTime {

	ST_ON_TIME(LocalTime.of(9, 30, 0)),
	ST_OFF_TIME(LocalTime.of(17, 40, 0)),
	EMP_ON_TIME(LocalTime.of(9, 0, 0)),
	EMP_OFF_TIME(LocalTime.of(18, 0, 0));

	private final LocalTime strTime;

	/**
	 * @return 기준시간
	 */
	public LocalTime strTime() {
		return strTime;
	}

}
