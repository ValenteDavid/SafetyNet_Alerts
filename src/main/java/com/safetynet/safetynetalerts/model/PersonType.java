package com.safetynet.safetynetalerts.model;

public enum PersonType {
	CHILD,
	ADULT;

	private static final int MAX_AGE = 18;

	public static PersonType get(int age) {
		if (age <= MAX_AGE) {
			return PersonType.CHILD;
		} else {
			return PersonType.ADULT;
		}
	}

}
