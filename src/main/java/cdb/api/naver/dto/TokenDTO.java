package cdb.api.naver.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenDTO {

	private String access_token;

	private String refresh_token;

	private String scope;

	private String token_type;

	private String expires_in;

}
