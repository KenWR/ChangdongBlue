package cdb.domain.dto;

import java.time.LocalDateTime;

import cdb.domain.etc.enums.BoardCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeBoardDTO {

	private long no;

	private BoardCategory boardCategory;

	private String content;

	private String title;

	private String employeeName;

	private long employeeNo;

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

}
