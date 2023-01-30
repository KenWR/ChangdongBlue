package cdb.domain.etc.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardCategory {

	NOTICE("CATEGORY_NOTICE"),
	TEACHER("CATEGORY_TEACHER"),
	EMPLOYEE("CATEGORY_EMPLOYEE");

	private final String boardCategory; // getBoardCategory();

}