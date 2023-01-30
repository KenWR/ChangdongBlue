package cdb.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentAttListDTO {

	private long no;// std_no

	private String name;// 학생

	private String phone;// 학생전화

	private String status;

	private LocalTime onTime;

	private LocalTime offTime;

	private LocalDate day;

}
