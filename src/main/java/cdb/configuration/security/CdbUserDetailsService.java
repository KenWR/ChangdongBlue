package cdb.configuration.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cdb.domain.repository.EmployeeRepository;
import cdb.domain.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CdbUserDetailsService implements UserDetailsService {

	private final TeacherRepository tr;

	private final EmployeeRepository er;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		final var employee = er.findByEmail(email);
		if (employee.isPresent()) return new CdbUser(employee.get());
		return new CdbUser(
				tr.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + "dose not exist")));
	}

}
