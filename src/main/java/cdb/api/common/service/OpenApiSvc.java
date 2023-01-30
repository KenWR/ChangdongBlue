package cdb.api.common.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.ui.Model;

public interface OpenApiSvc {

	void getTokenAsDTO(String code, Model model) throws MalformedURLException, IOException;

}
