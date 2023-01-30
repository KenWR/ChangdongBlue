package cdb.domain.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.AttendanceEntity;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

	Optional<AttendanceEntity> findByDay(LocalDate localDate);

	Optional<AttendanceEntity> findByOffTimeIsNull();

}
