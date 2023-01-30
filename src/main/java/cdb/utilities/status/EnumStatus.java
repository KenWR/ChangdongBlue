package cdb.utilities.status;

public enum EnumStatus implements EnumType {

	ON_TIME("시작"),
	OFF_TIME("끝");

	private final String value;

	EnumStatus(final String value) {
		this.value = value;
	}

	@Override
	public String getName() { return name(); }

	@Override
	public String getValue() { return value; }

}
