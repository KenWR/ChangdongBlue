package cdb.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cdb.domain.etc.BaseTime;
import cdb.domain.etc.enums.BoardCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Board")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	private String title; // 제목

	private String content; // 내용

	@Enumerated(EnumType.STRING)
	private BoardCategory boardCategory; // 게시판 분류

	@ManyToOne(cascade = CascadeType.PERSIST)
	private TeacherEntity teacher;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private EmployeeEntity employee;

	public BoardEntity addCategory(final BoardCategory boardCategory) {
		this.boardCategory = boardCategory;
		return this;
	}

}