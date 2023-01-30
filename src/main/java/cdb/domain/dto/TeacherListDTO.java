package cdb.domain.dto;

import java.util.Set;

import cdb.domain.etc.enums.CdbRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherListDTO {

	private long no;

	private String name;

	private String email;

	private String phone;

	private String addr;

	private Set<CdbRole> roles;

}
