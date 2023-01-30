package cdb.utilities.status;

import lombok.ToString;

@ToString
public class EnumTypeValue {

	private final String name;

	private final String value;

	public EnumTypeValue(final EnumType enumType) {
		name = enumType.getName();
		value = enumType.getValue();
	}

	public String getName() { return name; }

	public String getValue() { return value; }

}
