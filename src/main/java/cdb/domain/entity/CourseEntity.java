package cdb.domain.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cdb.domain.etc.BaseTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Course")
@Builder
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseEntity extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	private String title; // 과정명

	private int roomNum; // 반번호

	private int minSt; // 최소 정원

	private int maxSt; // 최대 정원

	private int price;

	private LocalDate startDate; // 개강 예정일

	private LocalDate endDate; // 종강 예정일

	@ManyToOne
	private TeacherEntity teacher;

	@OneToMany(mappedBy = "no")
	private List<StudentCourseEntity> studentCourses;

}