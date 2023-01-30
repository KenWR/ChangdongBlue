package cdb.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

	List<DepartmentEntity> findAllByRank(int i);

	Optional<DepartmentEntity> findByTitle(String title);

}
