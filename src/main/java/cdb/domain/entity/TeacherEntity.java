package cdb.domain.entity;

import java.time.LocalDate;
import java.util.HashSet;
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
@Table(name = "Teacher")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TeacherEntity extends BaseTime implements CdbAccCommon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false/* , unique = true */)
	private String phone;

	@Column(nullable = false)
	private String addr;

	private boolean gender;

	@Column(nullable = false)
	private LocalDate dateOfBirth;

	@Embedded
	private PasswordEm passwordEm;

	@Enumerated(EnumType.STRING)
	@CollectionTable(joinColumns = @JoinColumn(name = "teacherNo"), name = "TeacherRole")
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<CdbRole> roles = new HashSet<>();

	@Builder
	public TeacherEntity(final String name, final String email, final String phone, final String addr,
			final boolean gender, final LocalDate dateOfBirth, final String password) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.addr = addr;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		passwordEm = PasswordEm.builder().password(password).build();
	}

	public TeacherEntity addRole(final CdbRole role) {
		roles.add(role);
		return this;
	}

	public void setPasswordEm(final PasswordEm passwordEm) { this.passwordEm = passwordEm; }

}
