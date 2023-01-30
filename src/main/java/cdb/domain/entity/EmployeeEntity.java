package cdb.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cdb.domain.entity.embeddable.PasswordEm;
import cdb.domain.etc.BaseTime;
import cdb.domain.etc.CdbAccCommon;
import cdb.domain.etc.enums.CdbRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Employee")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeEntity extends BaseTime implements CdbAccCommon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String addr;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	private boolean gender;

	@Embedded
	private PasswordEm passwordEm;

	@JoinColumn(name = "DeptNo")
	@ManyToOne
	private DepartmentEntity department;

	@ManyToOne
	@JoinColumn(name = "ManagerNo")
	private EmployeeEntity manager;

	@OneToMany(mappedBy = "manager")
	private List<EmployeeEntity> employee = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	@CollectionTable(joinColumns = @JoinColumn(name = "EmployeeNo"), name = "EmployeeRole")
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<CdbRole> roles = new HashSet<>();

	@Builder
	public EmployeeEntity(final String name, final String email, final String phone, final String addr,
			final LocalDate dateOfBirth, final boolean gender, final String password, final DepartmentEntity department,
			final EmployeeEntity manager, final List<EmployeeEntity> employee) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		passwordEm = PasswordEm.builder().password(password).build();
		this.department = department;
		this.manager = manager;
		this.employee = employee;
	}

	public EmployeeEntity addRole(final CdbRole role) {
		roles.add(role);
		return this;
	}

	public void setDepartment(final DepartmentEntity department) { this.department = department; }

	public void setManager(final EmployeeEntity manager) { this.manager = manager; }

	public void setPasswordEm(final PasswordEm passwordEm) { this.passwordEm = passwordEm; }

}
