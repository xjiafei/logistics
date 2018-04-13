package com.winterframework.logistics.common.enums;

public enum FrequencyType {
	MIN("min", 1), HOUR("hour", 2), DAY("day", 3);
	private int _value;
	private String _key;

	FrequencyType(String key, int value) {
		this._key = key;
		this._value = value;
	}

	public int getValue() {
		return _value;
	}

	public static String getKey(int value) {
		FrequencyType[] values = FrequencyType.values();
		for (FrequencyType frequencyType : values) {
			if (frequencyType.getValue() == value) {
				return frequencyType.getKey();
			}
		}
		return null;
	}

	public String getKey() {
		return _key;
	}
}
