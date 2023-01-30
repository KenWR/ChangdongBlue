package cdb;

import java.time.LocalDate;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import cdb.domain.entity.BoardEntity;
import cdb.domain.entity.DepartmentEntity;
import cdb.domain.entity.DocEntity;
import cdb.domain.entity.EmployeeEntity;
import cdb.domain.entity.StudentEntity;
import cdb.domain.entity.TeacherEntity;
import cdb.domain.etc.enums.BoardCategory;
import cdb.domain.etc.enums.CdbRole;
import cdb.domain.etc.enums.DocStatus;
import cdb.domain.etc.enums.DocType;
import cdb.domain.repository.BoardRepository;
import cdb.domain.repository.DocRepository;
import cdb.domain.repository.EmployeeRepository;
import cdb.domain.repository.StudentRepository;
import cdb.domain.repository.TeacherRepository;

@SpringBootTest
public class BoardTest {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private StudentRepository seRepo;

	@Autowired
	private TeacherRepository teRepo;

	@Autowired
	private DocRepository docRepository;

	@Autowired
	private PasswordEncoder pe;

	// @Test
	void 선생등록테스트() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			final var te = TeacherEntity.builder()
																	.name("선생양시현" + i)
																	.email("te1" + i)
																	.phone("010-1234-1234" + i)
																	.addr("테스트 주소" + i)
																	.dateOfBirth(LocalDate.now())
																	.password(pe.encode("1234"))
																	// .passwordEm(PasswordEm.builder().password(pe.encode("1234")).build())
																	.build()
																	.addRole(CdbRole.TEMP)
																	.addRole(CdbRole.TEACHER);
			teRepo.save(te);
		});
	}

	// @Test
	void 직원등록테스트() {
		IntStream.rangeClosed(1, 2).forEach(i -> {
			final var e = EmployeeEntity.builder()
																	.name("테스트" + i)
																	.email("test" + i)
																	.phone("010-1234-1234" + i)
																	.addr("테스트 주소" + i)
																	.dateOfBirth(LocalDate.now())
																	.password(pe.encode("1234"))
																	// .passwordEm(PasswordEm.builder().password(pe.encode("1235")).build())
																	.build()
																	.addRole(CdbRole.STAFF);
			employeeRepository.save(e);
		});
	}

	// @Test
	void 학생등록테스트() {
		IntStream.rangeClosed(1, 30).forEach(i -> {
			final var se = StudentEntity.builder()
																	.name("학생" + i)
																	.email("email" + i)
																	.phone("010" + i)
																	.addr("주소" + i)
																	.gender(true)
																	.build();
			seRepo.save(se);
		});
	}

	// @Test
	void doc등록테스트() {
		IntStream.rangeClosed(1, 3).forEach(i -> {

			final var te = TeacherEntity.builder()
																	.name("양시현" + i)
																	.email("test" + i)
																	.phone("010-1234-1234" + i)
																	.addr("테스트 주소" + i)
																	// .passwordEm(PasswordEm.builder().password(pe.encode("1234")).build())
																	.build();
			teRepo.save(te);

			final var de = DocEntity.builder()
															.title("테스트제목" + i)
															.content("테스트내용" + i)
															.teacher(te)
															.docStatus(DocStatus.DEFAULT)
															.docType(DocType.VACATION)
															.build();
			docRepository.save(de);

		});
	}

	// @Test
	void EboardInsertTest() {
		IntStream.rangeClosed(1, 30).forEach(i -> {
			boardRepository.save(
					BoardEntity	.builder()
											.title("E테스트제목1" + i)
											.content("E테스트내용1" + i)
											.employee(EmployeeEntity.builder()
																							.name("직원" + i)
																							.phone("1234-1234" + i)
																							.addr("주소" + i)
																							// .passwordEm(PasswordEm.builder().password("1234").build())
																							.department(DepartmentEntity.builder().title("부서1" + i).build())
																							.build())
											.build()
											.addCategory(BoardCategory.EMPLOYEE));
		});
	}

	// @Test
	void TboardInsertTest() {
		IntStream.rangeClosed(1, 30).forEach(i -> {
			boardRepository.save(BoardEntity.builder()
																			.title("테스트제목" + i)
																			.content("테스트내용i" + i)
																			.teacher(TeacherEntity.builder()
																														.name("양시현" + i)
																														.email("test" + i)
																														.phone("010-1234-1234" + i)
																														.addr("테스트 주소" + i)
																														// .passwordEm(PasswordEm.builder().password("1234").build())
																														.build())
																			.build()
																			.addCategory(BoardCategory.TEACHER));
		});
	}

}