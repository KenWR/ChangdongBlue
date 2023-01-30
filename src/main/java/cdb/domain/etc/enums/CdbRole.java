package cdb.domain.etc.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CdbRole {

	HR("인사", "ROLE_HR", 30),
	TEACHER("강사", "ROLE_TEACHER", 45),
	STAFF("일반", "ROLE_STAFF", 45),
	TEMP("비정규직", "ROLE_TEMP", 90);

	private final String korRole;

	private final String prefixedRole;

	private final int changePasswordPeriod;

	public int changePasswordPeriod() {
		return changePasswordPeriod;
	}

	public String korRole() {
		return korRole;
	}

	public String prefixedRole() {
		return prefixedRole;
	}

}
