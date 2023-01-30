package cdb.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.TeacherEntity;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

	void deleteByEmail(String string);

	void existsByEmail(String email);

	Optional<TeacherEntity> findByEmail(String email);

}
