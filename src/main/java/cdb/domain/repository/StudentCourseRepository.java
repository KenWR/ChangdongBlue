package cdb.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.StudentCourseEntity;

public interface StudentCourseRepository extends JpaRepository<StudentCourseEntity, Long> {

	List<StudentCourseEntity> findByCourse(long no);

	List<StudentCourseEntity> findByCourseNoIsNull();

	StudentCourseEntity findByStudentNo(long sno);

	List<StudentCourseEntity> findByCourseNo(long courseNo);

}
