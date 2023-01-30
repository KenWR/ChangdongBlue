package cdb.domain.etc.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocType {

	VACATION("휴가신청서"),
	ABSENCE("결근신청서"), // 시현 - 추가했습니다. 승인된 결근/휴가문서를 바탕으로 직원과 선생님을 조회 -> 출석 결석OR연차 처리 합니다.
	ITEM("부자재신청서"),
	COURSE("강의개설신청서");

	private final String korDocType;

}
