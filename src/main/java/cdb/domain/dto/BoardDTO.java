package cdb.domain.dto;

import java.time.LocalDateTime;

import cdb.domain.etc.enums.BoardCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {

	private long boardNo;

	private BoardCategory boardCategory;

	private String title;

	private String content;

	private String writer;

	private long writerNo;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

}
