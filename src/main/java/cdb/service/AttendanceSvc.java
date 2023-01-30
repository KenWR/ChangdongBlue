package cdb.service;

import org.springframework.ui.Model;

import cdb.configuration.security.CdbUser;

public interface AttendanceSvc {

	void getOffAtt(Model mv, CdbUser user);

	void getOnAtt(Model mv, CdbUser user);

}
