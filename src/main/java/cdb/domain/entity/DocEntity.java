package cdb.domain.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cdb.domain.etc.BaseTime;
import cdb.domain.etc.enums.DocStatus;
import cdb.domain.etc.enums.DocType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Doc")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DocEntity extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	private String title;

	private String content;

	// 시현-휴가또는 연차 입력시 날짜 데이터가 필요해 시작일과 종료일 컬럼 추가했습니다. 다른 문서는 작성하지 않고 null입니다.
	private LocalDate startDate;

	// 시현-휴가또는 연차 입력시 날짜 데이터가 필요해 시작일과 종료일 컬럼 추가했습니다. 다른 문서는 작성하지 않고 null입니다.
	private LocalDate endDate;

	@ManyToOne
	private EmployeeEntity employee;

	@ManyToOne
	private TeacherEntity teacher;

	@Enumerated(EnumType.STRING)
	private DocType docType;

	@Enumerated(EnumType.STRING)
	private DocStatus docStatus;

	public void getDocStatus(final DocStatus getStatus) {
		docStatus = DocStatus.APPROVAL;
	}

	public String localDateToString(final LocalDate entityDate) {
		final var date = entityDate;
		return date.toString();
	}

	public void setDocStatus(final DocStatus docStatus) { this.docStatus = docStatus; }

	// 편의메서드
	public DocEntity update(final DocStatus docStatus) {
		this.docStatus = docStatus;
		return this;
	}

	public void updateDocStatus(final DocStatus enrollment) {
		docStatus = enrollment;
	}

}
