package cdb.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.ImgFileEntity;

public interface ImgFileRepository extends JpaRepository<ImgFileEntity, Long> {

}
