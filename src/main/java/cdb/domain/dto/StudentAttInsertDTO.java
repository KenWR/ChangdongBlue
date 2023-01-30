package cdb.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import cdb.domain.etc.enums.AttendanceStudentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentAttInsertDTO {

	private long no;

	private String name;

	private String phone;

	private AttendanceStudentStatus status;

	private LocalTime onTime;

	private LocalTime offTime;

	private LocalDate day;

}
