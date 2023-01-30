package cdb.domain.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cdb.domain.etc.BaseTime;
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
@Table(name = "Attendance")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AttendanceEntity extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@Enumerated(EnumType.STRING)
	private AttendanceStatus status; // 상태

	private LocalTime onTime; // 출근시간

	private LocalTime offTime; // 퇴근시간

	private LocalDate day; // 오늘날짜

	public AttendanceEntity updateStatusAndTime(final AttendanceStatus status, final LocalTime offTime) {
		this.status = status;
		this.offTime = offTime;
		return this;
	}

}
