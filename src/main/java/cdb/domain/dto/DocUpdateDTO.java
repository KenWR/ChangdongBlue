package cdb.domain.dto;

import cdb.domain.etc.enums.DocStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocUpdateDTO {

	private long no;

	private long docNo;

	private DocStatus docStatus;

}
