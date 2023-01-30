package cdb.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.AttendanceStudentEntity;

public interface AttendanceStudentRepository extends JpaRepository<AttendanceStudentEntity, Long> {

	List<AttendanceStudentEntity> findAllByDay(LocalDate now);

	Optional<AttendanceStudentEntity> findByNo(long no);

	List<AttendanceStudentEntity> findByDayAndStudentNoIn(LocalDate now, List<Long> numbers);

}
