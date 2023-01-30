package cdb.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "StudentCourse")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StudentCourseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@ManyToOne
	private StudentEntity student;

	@ManyToOne
	private CourseEntity course;

	public StudentCourseEntity update(final StudentEntity student, final CourseEntity course) {
		this.student = student;
		this.course = course;
		return this;
	}

	// @ManyToOne
	// private AttendanceStudentEntity attendance; - 0126 시현 주석처리

}
