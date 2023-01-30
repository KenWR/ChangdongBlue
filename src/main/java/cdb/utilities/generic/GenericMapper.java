package cdb.utilities.generic;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import cdb.domain.entity.embeddable.PasswordEm;

public interface GenericMapper<D, E> {

	default String password(final PasswordEm password) {
		return password(password);
	}

	default PasswordEm password(final String password) {
		return PasswordEm.builder().password(password).build();
	}

	/**
	 * @param entity DTO로 변환할 엔티티
	 * @return 해당타입의 DTO
	 */
	D toDTO(E entity);

	List<D> toDTOs(List<E> entityList);

	List<E> toEntities(List<D> dtoList);

	/**
	 * @param dto 엔티티로 변환할 DTO
	 * @return 해당타입의 엔티티
	 */
	E toEntity(D dto);

	/**
	 * @param dto    변경사항이 있는 DTO
	 * @param entity 업데이트 대상 엔티티
	 */
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void update(D dto, @MappingTarget E entity);

}
