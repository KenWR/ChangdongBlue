package cdb.domain.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import cdb.domain.etc.enums.CdbRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeListDTO {

	@NotEmpty
	private long no;

	@NotEmpty
	private String name;

	@NotEmpty
	private String email;

	@NotEmpty
	private String phone;

	@NotEmpty
	private String addr;

	@NotEmpty
	private Set<CdbRole> roles;

}
