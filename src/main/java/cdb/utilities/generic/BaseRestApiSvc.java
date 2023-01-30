package cdb.utilities.generic;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseRestApiSvc<E, D> {

	protected final Class<? extends JpaRepository<E, ? extends Number>> repo;

	public BaseRestApiSvc(final Class<? extends JpaRepository<E, ? extends Number>> repo) {
		this.repo = repo;
	}

}
