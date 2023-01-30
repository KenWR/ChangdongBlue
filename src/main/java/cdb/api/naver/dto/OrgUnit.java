package cdb.api.naver.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrgUnit {

	private int domainId;

	private String orgUnitId;

	private String orgUnitExternalKey;

	private String orgUnitName;

	private List<I18nName> i18nNames;

	private String email;

	private String description;

	private boolean visible;

	private String parentOrgUnitId;

	private String parentExternalKey;

	private int displayOrder;

	private int displayLevel;

	private String[] aliasEmails;

	private boolean canReceiveExternalMail;

	private boolean useMessage;

	private boolean useNote;

	private boolean useCalendar;

	private boolean useTask;

	private boolean useFolder;

	private boolean useServiceNotification;

	private List<OrgUnitAllowedMember> membersAllowedToUseOrgUnitEmailAsRecipient;

	private List<OrgUnitAllowedMember> membersAllowedToUseOrgUnitEmailAsSender;

}
