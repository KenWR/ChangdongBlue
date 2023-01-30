package cdb.domain.entity.embeddable;

import java.time.LocalDate;

import javax.persistence.Embeddable;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordEm {

	private String password;

	private int attempts;

	private LocalDate expirationDate;

	@Builder
	public PasswordEm(final String password) {
		this.password = password;
		expirationDate = LocalDate.now().plusDays(90);
	}

	public void addAttempts() {
		attempts++;
	}

	public boolean attemptsLimit() {
		return attempts >= 5;
	}

	public boolean matchesPassword(final PasswordEncoder pe, final String password) {
		return pe.matches(password, pe.encode(password));
	}

	public void resetAttempts() {
		attempts = 0;
	}

	public void updatePassword(final String newPassword) {
		password = newPassword;
		expirationDate = LocalDate.now().plusDays(90);
		resetAttempts();
	}

}
