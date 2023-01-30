package cdb;

import java.time.LocalDate;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import cdb.domain.entity.EmployeeEntity;
import cdb.domain.entity.TeacherEntity;
import cdb.domain.etc.enums.CdbRole;
import cdb.domain.repository.EmployeeRepository;
import cdb.domain.repository.TeacherRepository;
import cdb.utilities.status.EnumTypeFactory;

@EnableJpaAuditing
@SpringBootApplication
public class CdbApplication {

	public static void main(final String[] args) {
		SpringApplication.run(CdbApplication.class, args);
	}

	@Bean
	@Transactional
	ApplicationRunner applicationRunner(final EmployeeRepository er, final PasswordEncoder pe, final TeacherRepository tr,
			final EnumTypeFactory etf) {
		return args -> {
			if (er.findByEmail("e").isPresent() || tr.findByEmail("t").isPresent()) return;
			er.save(EmployeeEntity.builder()
														.name("홍길동")
														.phone("01012341234")
														.email("e")
														.dateOfBirth(LocalDate.of(1991, 10, 5))
														.addr("노원구")
														.password(pe.encode("ee"))
														.build()
														.addRole(CdbRole.HR));
			tr.save(TeacherEntity	.builder()
														.name("둘리")
														.phone("01043214321")
														.dateOfBirth(LocalDate.of(1988, 7, 22))
														.email("t")
														.addr("강북구")
														.password(pe.encode("tt"))
														.build()
														.addRole(CdbRole.TEACHER));
			// etf.get("Status");
		};
	}

}
