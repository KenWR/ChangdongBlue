package cdb.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeEvaluationInsertDTO {

	private long empNo;

	private String question;

	private int score;

}
