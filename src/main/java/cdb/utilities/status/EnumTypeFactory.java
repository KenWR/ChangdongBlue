package cdb.utilities.status;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnumTypeFactory {

	private final Map<String, List<EnumTypeValue>> factory;

	public EnumTypeFactory(final Map<String, List<EnumTypeValue>> factory) {
		this.factory = factory;
	}

	public List<EnumTypeValue> get(final String key) {
		return factory.get(key);
	}

	public Map<String, List<EnumTypeValue>> getFactory() { return factory; }

	public void put(final String key, final Class<? extends EnumType> enumTypeImpl) {
		factory.put(key, toEnumValues(enumTypeImpl));
	}

	private List<EnumTypeValue> toEnumValues(final Class<? extends EnumType> enumTypeImpl) {
		return Arrays.stream(enumTypeImpl.getEnumConstants()).map(EnumTypeValue::new).collect(Collectors.toList());
	}

}
