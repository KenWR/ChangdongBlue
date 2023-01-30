package cdb.domain.etc;

import java.util.Set;

import cdb.domain.entity.embeddable.PasswordEm;
import cdb.domain.etc.enums.CdbRole;

public interface CdbAccCommon {

	String getEmail();

	String getName();

	long getNo();

	PasswordEm getPasswordEm();

	Set<CdbRole> getRoles();

}
