package cdb.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "UserAttendance")
@Builder
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAttendanceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@ManyToOne
	private EmployeeEntity employee;

	@ManyToOne
	private TeacherEntity teacher;

	@ManyToOne
	private AttendanceEntity attendance;

}