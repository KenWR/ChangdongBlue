package cdb.domain.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseListDTO {

	private long no;

	private String title;

	private int roomNum;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	private int maxSt;

	private int minSt;

	private int price;

	private long studentNo;

	private long teacherNo;

	private String name;

}
