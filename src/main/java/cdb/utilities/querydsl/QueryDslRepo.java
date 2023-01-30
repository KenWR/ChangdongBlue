package cdb.utilities.querydsl;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryDslRepo {

	private final JPAQueryFactory jpaQueryFactory;

	// public @Nullable EmployeeEntity findByEmail(String email) {
	// return jpaQueryFactory.selectFrom(employeeEntity)
	// .join(employeeEntity.employee)
	// .where(employeeEntity.email.eq(email))
	// .fetchOne();
	// }

}
