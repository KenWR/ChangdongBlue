package cdb.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cdb.domain.etc.BaseTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Department")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DepartmentEntity extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	private String title; // emp position과 겹치는것 같습니다.

	private int rank;

	@JoinColumn
	@ManyToOne
	private DepartmentEntity parent; // 부서명

	// 이것외에도 employeeEntity에 연관관계 joinColumn추가 deptNo로

}
