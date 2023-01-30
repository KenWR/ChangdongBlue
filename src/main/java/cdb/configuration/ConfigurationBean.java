package cdb.configuration;

import java.util.LinkedHashMap;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import cdb.utilities.status.EnumStatus;
import cdb.utilities.status.EnumTypeFactory;

@Configuration
public class ConfigurationBean {

	@Bean
	EnumTypeFactory enumTypeFactory() {
		final var enumTypeFactory = new EnumTypeFactory(new LinkedHashMap<>());
		enumTypeFactory.put("Status", EnumStatus.class);
		return enumTypeFactory;
	}

	@Bean
	JPAQueryFactory jpaQueryFactory(final EntityManager entityManager) {
		return new JPAQueryFactory(entityManager);
	}

}
