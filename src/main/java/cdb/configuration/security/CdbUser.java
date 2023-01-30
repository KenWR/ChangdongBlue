package cdb.configuration.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import cdb.domain.etc.CdbAccCommon;
import cdb.domain.etc.enums.CdbRole;
import lombok.Getter;

@Getter
public class CdbUser extends User {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private long no;

	private String name;

	private String email;

	private boolean isEmployee;

	public CdbUser(final CdbAccCommon entity) {
		this(entity.getEmail(), entity.getPasswordEm().getPassword(),
				entity.getRoles().stream().map(t -> new SimpleGrantedAuthority(t.prefixedRole())).collect(Collectors.toSet()));
		no = entity.getNo();
		name = entity.getName();
		email = entity.getEmail();
		isEmployee = !entity.getRoles().contains(CdbRole.TEACHER);
	}

	public CdbUser(final String username, final String password,
			final Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

}