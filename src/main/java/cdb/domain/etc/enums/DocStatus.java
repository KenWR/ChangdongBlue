package cdb.domain.etc.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocStatus {

	DEFAULT("상신"),
	APPROVAL("승인"),
	HOLD("보류"),
	REJECT("반려"),
	ENROLLMENT("행정처리완료");// 0121 - 승인된 연차,결근 문서를 휴가 등록하면 행정처리완료 상태로 바뀝니다.

	private final String korDocStatus;

}
