package cdb.domain.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.UserAttendanceEntity;

public interface UserAttendanceRepository extends JpaRepository<UserAttendanceEntity, Long> {

	Optional<UserAttendanceEntity> findByAttendanceOffTimeIsNullAndEmployeeNo(long no);

	Optional<UserAttendanceEntity> findByAttendanceOffTimeIsNullAndTeacherNo(long no);

	Optional<UserAttendanceEntity> findByEmployeeNoAndAttendanceDay(long no, LocalDate now);

	Optional<UserAttendanceEntity> findByTeacherNoAndAttendanceDay(long no, LocalDate now);

}
