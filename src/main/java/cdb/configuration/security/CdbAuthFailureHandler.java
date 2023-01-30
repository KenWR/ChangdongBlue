package cdb.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cdb.domain.repository.EmployeeRepository;
import cdb.domain.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CdbAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private final TeacherRepository tr;

	private final EmployeeRepository er;

	@Override
	@Transactional
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException exception) throws IOException, ServletException {
		super.onAuthenticationFailure(request, response, exception);
		final var email = request.getParameter("email");
		final var empEntity = er.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
		if (!empEntity.getPasswordEm().attemptsLimit())
			empEntity.getPasswordEm().addAttempts();
		else tr.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email)).getPasswordEm().addAttempts();
		if (exception instanceof PasswordAttemptsExceededExpectation) response.sendRedirect("/sign/signin?error");

	}

}
