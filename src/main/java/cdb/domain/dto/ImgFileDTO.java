package cdb.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImgFileDTO {

	private final long no;

	private final String orgName;

	private final String newName;

	private final String url;

	private final boolean defImg;

	// 편의필드
	private final String imgUrl;

	public ImgFileDTO(final ImgFileDTO e) {
		no = e.getNo();
		orgName = e.getNewName();
		newName = e.getNewName();
		url = e.getUrl();
		defImg = e.isDefImg();
		imgUrl = url + newName;
	}

}
