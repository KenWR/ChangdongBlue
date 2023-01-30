package cdb.controller.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cdb.domain.dto.CourseInsertDTO;
import cdb.domain.dto.StudentCourseInsertDTO;
import cdb.domain.entity.StudentCourseEntity;
import cdb.service.CourseSvc;
import cdb.service.StudentCourseSvc;
import cdb.service.StudentSvc;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CourseController {

	private final CourseSvc cSvc;

	private final StudentSvc sSvc;

	private final StudentCourseSvc scSvc;

	// private final AttendanceStudentSvc asSvc;

	@GetMapping("/course")
	public String course(final Model model) {
		cSvc.findCourse(model);
		return "course/course";
	}

	@GetMapping("/course-info")
	public String courseInfo() {
		return "course/info";
	}

	// addr cannot be null
	// 강의 등록 (o)
	@PostMapping("/info-course")
	public String courseInfo(final CourseInsertDTO dto) {
		cSvc.save(dto);
		return "course/info";
	}

	// 학생 출결 관리 페이지로 이동합니다. - 코스를 나열해서 보여주어야 합니다.
	@GetMapping(value = "/students/att/on")
	public String findCourse(final Model model) {
		cSvc.findCourse(model);
		// asSvc.findAllStuAtt(model);
		// asSvc.findTime(model);
		return "students/students-attendance";
	}

	// 코스리스트중 하나를 선택하면 코스를 듣는 학생들을 보여줍니다. / ajax로 처리해봅니다. / long no = 코스PK
	// @ResponseBody
	// @Transactional
	// @PostMapping(value = "/students/att/on")
	// public String findStudentsInCourse(long no, Model model, StudentAttInsertDTO
	// dto) {
	// // cSvc.findStudentsInCourse(no, model);
	// cSvc.findCourse(model);
	// asSvc.saveDayAndStatus(dto, no);
	// return "students/students-list";
	// }

	// @PostMapping(value = "/students/att/on")
	// public String findStudentsInCourse(final long no, final Model model) {
	// cSvc.findStudentsInCourse(no, model);
	// cSvc.findCourse(model);
	// return "students/students-list";
	// }
	// 학생출석기능, 지원님 이어서
	@PostMapping(value = "/check")
	public void regStudentAtt(final long cNo, final long sNo) {
		cSvc.regStudentAtt(cNo, sNo);
	}

	// 이건 병합해야함
	// 강의에 학생을 등록합니다. / 이미 강의가 등록된 학생은 나오지 않아요
	// 지금 학생 조회 쿼리면 한 학생은 여러 강의를 동시에 들을수 없어요
	@GetMapping(value = "/students/course/on")
	public String regStudentToCourse(final Model model) {
		sSvc.findStudentWhoNonRegCourse(model);
		cSvc.findCourse(model);
		return "students/student-course";
	}

	// 이건 병합해야함
	@PostMapping(value = "/students/course/on") //// 학생, 강의 pk를 StudentCourseEntity에 담아내는 컨트롤러
	public String regStudentToCourse(final StudentCourseInsertDTO studentCourseInsertDTO,
			StudentCourseEntity studentCourseEntity) {
		scSvc.save(studentCourseInsertDTO, studentCourseEntity);
		// asSvc.saveAtt(studentCourseInsertDTO);
		return "redirect:/students/course/on";
	}

}