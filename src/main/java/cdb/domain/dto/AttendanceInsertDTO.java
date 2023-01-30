package cdb.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import cdb.domain.entity.EmployeeEntity;
import cdb.domain.entity.TeacherEntity;
import cdb.domain.etc.enums.AttendanceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceInsertDTO {

	private long no;

	private LocalDate day;

	private LocalTime onTime;

	private LocalTime offTime;

	private AttendanceStatus status;

	private EmployeeEntity employee;

	private TeacherEntity teacher;

}
