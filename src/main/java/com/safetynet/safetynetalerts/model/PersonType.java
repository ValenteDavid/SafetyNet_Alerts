package com.safetynet.safetynetalerts.model;

public enum PersonType {
	CHILD(18),
	ADULT(200);

	private final int maxAge;

	PersonType(int maxAge) {
		this.maxAge = maxAge;
	}

	public int getMaxAge() {
		return maxAge;
	}
}
