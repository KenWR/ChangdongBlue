package cdb.domain.dto;

import java.time.LocalTime;

import cdb.domain.etc.enums.AttendanceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeAttDTO {

	private String name;

	private AttendanceStatus status;

	private LocalTime onTime;

	private LocalTime offTime;

}
