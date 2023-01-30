package cdb.api.naver.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;

import cdb.api.common.service.OpenApiSvc;
import cdb.api.naver.dto.OrgUnitsDTO;
import cdb.api.naver.dto.TokenDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NaverOpenApiSvcProc implements OpenApiSvc {

	@Value("${openapi.naver.org-unit.client-id}")
	private String ORG_C_ID;

	@Value("${openapi.naver.org-unit.client-s}")
	private String ORG_C_S;

	@Override
	public void getTokenAsDTO(final String code, final Model model) throws IOException {
		final var apiURL = new StringBuilder("https://auth.worksmobile.com/oauth2/v2.0/token");
		apiURL.append("?code=").append(code);
		apiURL.append("&client_id=").append(ORG_C_ID);
		apiURL.append("&client_secret=").append(ORG_C_S);
		apiURL.append("&grant_type=authorization_code");
		System.out.println(apiURL.toString());
		final var url = new URL(apiURL.toString());
		final var con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		final var responseCode = con.getResponseCode();
		String responseData = null;
		if (responseCode == HttpURLConnection.HTTP_OK) {
			responseData = readBody(con.getInputStream());
			System.out.println("정상");
		} else {
			responseData = readBody(con.getErrorStream());
			System.out.println("에러");
		}
		con.disconnect();

		// JSON형식 문자열데이터를 리턴
		System.out.println(responseData);
		final var objM = new ObjectMapper();

		// 필드 이름이 모두일치하고 getter,setter가 있을때 JSON을 클래스에 매핑해줌
		final var dto = objM.readValue(responseData, TokenDTO.class);

		model.addAttribute("list", getOrgUnitsFromTokenDTO(dto));
	}

	// 응답데이터를 스트림을 통해서 한줄씩읽어서 문자열로 리턴

	private OrgUnitsDTO getOrgUnitsFromTokenDTO(final TokenDTO dto) throws IOException {
		final var orgUnits = "https://www.worksapis.com/v1.0/orgunits";
		final var url = new URL(orgUnits);
		final var con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", "Bearer " + dto.getAccess_token());
		final var responseCode = con.getResponseCode();
		String responseData = null;
		if (responseCode == HttpURLConnection.HTTP_OK) {
			responseData = readBody(con.getInputStream());
			System.out.println("정상");
		} else {
			responseData = readBody(con.getErrorStream());
			System.out.println("에러");
		}
		con.disconnect();

		System.out.println(con.getResponseCode());

		final var objM = new ObjectMapper();
		return objM.readValue(responseData, OrgUnitsDTO.class);
	}

	private String readBody(final InputStream inputStream) throws IOException {
		final var streamReader = new InputStreamReader(inputStream);
		final var lineReader = new BufferedReader(streamReader);
		final var responseBody = new StringBuilder();
		String data;
		while ((data = lineReader.readLine()) != null) responseBody.append(data);
		lineReader.close();
		streamReader.close();
		return responseBody.toString();
	}

}
