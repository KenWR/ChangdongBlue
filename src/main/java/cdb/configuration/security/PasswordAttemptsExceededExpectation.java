package cdb.configuration.security;

import org.springframework.security.authentication.AccountStatusException;

public class PasswordAttemptsExceededExpectation extends AccountStatusException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public PasswordAttemptsExceededExpectation(final String msg) {
		super(msg);
	}

	public PasswordAttemptsExceededExpectation(final String msg, final Throwable cause) {
		super(msg, cause);
	}

}
