package cdb.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

	void existsByEmail(String email);

	Optional<StudentEntity> findByName(String name);

}
