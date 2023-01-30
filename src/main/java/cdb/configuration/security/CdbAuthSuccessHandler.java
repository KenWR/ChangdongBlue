package cdb.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cdb.domain.repository.EmployeeRepository;
import cdb.domain.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CdbAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final TeacherRepository tr;

	private final EmployeeRepository er;

	@Override
	@Transactional
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException, ServletException {
		super.onAuthenticationSuccess(request, response, authentication);
		final var user = (CdbUser) authentication.getPrincipal();
		final var accCommon = user.isEmployee() ? tr.findById(user.getNo()).get() : er.findById(user.getNo()).get();
		if (accCommon.getPasswordEm().attemptsLimit()) throw new PasswordAttemptsExceededExpectation("비밀번호 5번 불일치");
		accCommon.getPasswordEm().resetAttempts();
	}

}
