package cdb.domain.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cdb.domain.entity.BoardEntity;
import cdb.domain.etc.enums.BoardCategory;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	List<BoardEntity> findAllByBoardCategory(BoardCategory category, Pageable pageable);

}
