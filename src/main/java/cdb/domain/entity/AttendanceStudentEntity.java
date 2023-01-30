package cdb.domain.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import cdb.domain.etc.BaseTime;
import cdb.domain.etc.enums.AttendanceStudentStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Setter
@Table(name = "AttendanceStudent")
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class AttendanceStudentEntity extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@Enumerated(EnumType.STRING)
	private AttendanceStudentStatus status; // 상태

	private LocalTime onTime; // 입실시간

	private LocalTime offTime; // 퇴실시간

	private LocalDate day; //

	@ManyToOne
	private StudentEntity student; // 새로운 연관관계

	// @ManyToOne
	// private CourseEntity course; - 0126 시현 주석처리

	// @OneToMany(mappedBy = "no")
	// private List<StudentCourseEntity> studentCourses; - 0126 시현 주석처리

	public AttendanceStudentEntity updateOffTime(final AttendanceStudentStatus status, final LocalTime time) {
		this.status = status;
		offTime = time;
		return this;
	}

	public AttendanceStudentEntity updateOnTime(final AttendanceStudentStatus status, final LocalTime time) {
		this.status = status;
		onTime = time;
		return this;
	}

	public AttendanceStudentEntity addStaus(final LocalTime on, final LocalTime off) {// 0126 - 시현 추가

		if (on.isBefore(LocalTime.of(9, 30))) {// 9시 30이전 출석
			if (off.isBefore(LocalTime.of(18, 0))) {// 18시 이전 퇴실
				if (ChronoUnit.HOURS.between(on, off) <= 5)
					status = AttendanceStudentStatus.ABSENCE;
				else status = AttendanceStudentStatus.EARLY;
			} else status = AttendanceStudentStatus.ON;// 18시 이후 퇴실
			return this;
		}
		if (on.isAfter(LocalTime.of(9, 31))) {// 9시 이후 출석
			if (ChronoUnit.HOURS.between(on, off) <= 5)
				status = AttendanceStudentStatus.ABSENCE;
			else // 수업시간 4시간 이상
				status = AttendanceStudentStatus.LATE;
			return this;
		}
		return null;

	}

}