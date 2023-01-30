package cdb;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import cdb.domain.dto.reg.EmployeeInsertDTO;
import cdb.domain.entity.CourseEntity;
import cdb.domain.entity.EmployeeEntity;
import cdb.domain.entity.StudentEntity;
import cdb.domain.repository.AttendanceStudentRepository;
import cdb.domain.repository.CourseRepository;
import cdb.domain.repository.EmployeeRepository;
import cdb.domain.repository.StudentRepository;
import cdb.domain.repository.TeacherRepository;

@SpringBootTest
class CdbApplicationTests {

	@Autowired
	private EmployeeRepository er;

	@Autowired
	private TeacherRepository tr;

	@Autowired
	private PasswordEncoder pe;

	@Autowired
	private StudentRepository seRepo;

	@Autowired
	private AttendanceStudentRepository asRepo;

	@Autowired
	private CourseRepository cRepo;

	// @Test
	void 입력테스트() {
		IntStream	.range(0, 10)
							.forEach(t -> er.save(EmployeeEntity.builder()
																									.name(t + "")
																									.addr(t + "")
																									.email(t + "")
																									.phone(t + "")
																									// .passwordEm(PasswordEm.builder().password(pe.encode(t +
																									// "")).build())
																									.build()));
	}

	// @Test
	void 코스등록() {
		final var ce = CourseEntity.builder().no(4).title("역사").roomNum(4).minSt(22).maxSt(22).price(22).build();

		cRepo.save(ce);
	}

	// @Test
	void 학생등록테스트() {
		final var se = StudentEntity.builder()
																.no(10)
																.name("학생10")
																.phone("01011111120")
																.addr("노원10번지")
																.email("stu10@test.com")
																.gender(true)
																.build();
		seRepo.save(se);
	}

	// @Test
	// void 학생출석등록테스트() {
	// final AttendanceStudentEntity ase = AttendanceStudentEntity.builder()
	// .no(1)
	// .student(StudentEntity.builder()
	// .name("학생1")
	// .phone("01001")
	// .no(1)
	// .build())
	// .course(CourseEntity.builder()
	// .build())
	// .status(AttendanceStudentStatus.OFF)
	// .onTime(null)
	// .onTime(LocalTime.of(9, 20, 0))
	// .offTime(LocalTime.of(17, 50, 0))
	// .build();
	// asRepo.save(ase);

	// }

	// @Test
	void MapperTest() {
		final var dto = new EmployeeInsertDTO();
		dto.setAddr("a");
		dto.setEmail("a");
		dto.setName("a");
		dto.setPhone("aa");
		dto.setPassword("a");
		// EmployeeEntity e = mr.toEntity(dto);
		// er.save(e);
	}

}
