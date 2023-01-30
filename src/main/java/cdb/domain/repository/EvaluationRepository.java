package cdb.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.EvaluationEntity;

public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Long> {

}
