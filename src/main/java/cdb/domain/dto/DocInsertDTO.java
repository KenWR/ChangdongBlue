package cdb.domain.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import cdb.domain.etc.enums.DocType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocInsertDTO {

	private DocType docType;

	private String title;

	private String content;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

}
