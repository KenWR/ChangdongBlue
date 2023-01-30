package cdb.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

	void deleteByEmail(String string);

	void existsByEmail(String email);

	Optional<EmployeeEntity> findByEmail(String email);

	Optional<EmployeeEntity> findByName(String name);

}
