package cdb.domain.dto.reg;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInsertDTO {

	@NotEmpty
	private String name;

	@Email
	private String email;

	@NotEmpty
	private String phone;

	private String addr;

	private boolean gender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

}
