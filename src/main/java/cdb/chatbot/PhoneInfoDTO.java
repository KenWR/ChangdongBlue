package cdb.chatbot;

import java.time.LocalDate;

import cdb.domain.etc.enums.AttendanceStudentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PhoneInfoDTO {

	private String dept;

	private String phone;

	private String empName;

	private String stuName;

	private AttendanceStudentStatus status;

	private LocalDate day;

}
