package cdb.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.DocEntity;
import cdb.domain.etc.enums.DocStatus;
import cdb.domain.etc.enums.DocType;

public interface DocRepository extends JpaRepository<DocEntity, Long> {

	List<DocEntity> findAllByDocType(DocType docType);

	List<DocEntity> findByDocStatus(DocStatus docStatus);

	List<DocEntity> findByDocStatusAndDocType(DocStatus approval, DocType vacation);

	List<DocEntity> findByDocStatusAndDocTypeOrDocStatusAndDocType(DocStatus approval, DocType vacation,
			DocStatus approval2, DocType absence);

}
