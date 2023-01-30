package cdb.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cdb.configuration.security.CdbUser;
import cdb.domain.entity.AttendanceEntity;
import cdb.domain.entity.UserAttendanceEntity;
import cdb.domain.etc.enums.AttendanceStatus;
import cdb.domain.etc.enums.AttendanceTime;
import cdb.domain.repository.AttendanceRepository;
import cdb.domain.repository.EmployeeRepository;
import cdb.domain.repository.TeacherRepository;
import cdb.domain.repository.UserAttendanceRepository;
import cdb.service.AttendanceSvc;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceSvcProc implements AttendanceSvc {

	private final EmployeeRepository er;

	private final AttendanceRepository ar;

	private final TeacherRepository tr;

	private final UserAttendanceRepository ur;

	// 퇴근시간을 저장하는 과정입니다. - 시현
	@Override
	@Transactional
	public void getOffAtt(final Model mv, final CdbUser user) {
		final var no = user.getNo();

		if (user.isEmployee()) {
			final var eOffAtt = ur.findByAttendanceOffTimeIsNullAndEmployeeNo(no).get().getAttendance();
			eOffAtt.updateStatusAndTime(getStatusOff(eOffAtt), LocalTime.now());

		} else {
			final var tOffAtt = ur.findByAttendanceOffTimeIsNullAndTeacherNo(no).get().getAttendance();
			tOffAtt.updateStatusAndTime(getStatusOff(tOffAtt), LocalTime.now());

		}
		// 4시간 미만 근무는 무조건 EARLY(조퇴)
		// 9시 이전 출근 + 18시 이후 퇴근 했으면 OFF, 9시 이후 출근했으면 무조건 LATE
		// 오늘 하루 출석 결과 없으면 ABSENCE - 출석테이블에서 오늘 id로 검색한다.

		// 출근의 경우
		// 9이전 출근 ON, 9시 이후 출근 LATE, ABSENCE
		// ON -> OFF EARLY LATE
		// LATE -> EARLY LATE
		// ON과 LATE 조건 만들고 나누면 될듯?

	}

	// 출근시간을 저장하는 과정입니다. - 시현
	@Override
	@Transactional
	public void getOnAtt(final Model mv, final CdbUser user) {
		final var no = user.getNo();

		if (user.isEmployee()) {// 선생님인지 직원인지 구분

			if (ur.findByEmployeeNoAndAttendanceDay(no, LocalDate.now()).isEmpty())
				onAttWhoE(no);
			else System.out.println("중복된 직원 출석이 있습니다.");

		} else if (ur.findByTeacherNoAndAttendanceDay(no, LocalDate.now()).isEmpty())
			onAttWhoT(no);
		else System.out.println("중복된 선생님 출석이 있습니다.");
	}

	// 직원 출근시간 저장 - 시현
	@Transactional
	private void onAttWhoE(final long no) {
		final var at = ar.save(
				AttendanceEntity.builder().day(LocalDate.now()).onTime(LocalTime.now()).status(statusOn()).build());

		er.findById(no)
			.stream()
			.forEach(emp -> ur.save(UserAttendanceEntity.builder().attendance(at).employee(emp).build()));
	}

	// 선생님 출근시간 저장 - 시현
	@Transactional
	private void onAttWhoT(final long no) {
		final var at = ar.save(
				AttendanceEntity.builder().day(LocalDate.now()).onTime(LocalTime.now()).status(statusOn()).build());

		tr.findById(no).stream().forEach(te -> ur.save(UserAttendanceEntity.builder().attendance(at).teacher(te).build()));
	}

	// 9 to 6, 출근status 변경 메소드
	@Transactional
	private AttendanceStatus statusOn() {
		final var onTime = LocalTime.now();
		if (onTime.isAfter(AttendanceTime.EMP_ON_TIME.strTime())) return AttendanceStatus.LATE;// 지각
		return AttendanceStatus.ON;// 출석
	}

	// 퇴근 status변경 메소드
	@Transactional
	private AttendanceStatus getStatusOff(final AttendanceEntity result) {
		final var workTime = LocalTime.now().toSecondOfDay() - result.getOnTime().toSecondOfDay();// 퇴근시간 - 출근시간 == 근무시간
		if (workTime < 4 * 60 * 60) return AttendanceStatus.EARLY; // 4시간 미만 근무는 무조건 조퇴
		if (result.getOnTime().isBefore(AttendanceTime.EMP_ON_TIME.strTime())) return AttendanceStatus.OFF; // 퇴근시간 상관없이
																																																				// status를 OFF로
		return AttendanceStatus.LATE; // 9시이후 출근,4시간 이상 근무 했으면 퇴근시간 상관없이 status를LATE

	}

}// end:class