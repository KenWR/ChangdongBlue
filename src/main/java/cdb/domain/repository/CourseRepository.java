package cdb.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

	List<CourseEntity> findAllByStartDateBetween(LocalDate now, int a);

	List<CourseEntity> findAllByStudentCoursesStudentNoIsNotNull();

}
