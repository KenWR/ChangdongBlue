package cdb.domain.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cdb.domain.etc.enums.AttendanceStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Setter
@Table(name = "Calendar")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CalendarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@Enumerated(EnumType.STRING)
	private AttendanceStatus status; // 상태

	private LocalDate start; // 오늘날짜

	private LocalDate end;

	@ManyToOne
	private EmployeeEntity employee;

	@ManyToOne
	private TeacherEntity teacher;

}
