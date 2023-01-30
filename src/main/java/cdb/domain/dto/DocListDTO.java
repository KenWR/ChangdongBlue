package cdb.domain.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import cdb.domain.etc.enums.DocStatus;
import cdb.domain.etc.enums.DocType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocListDTO {

	private long no;

	private String title;

	private String content;

	private DocType docType;

	private DocStatus docStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	private String email;

	private String name;

}
