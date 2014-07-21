package com.oreon.cerebrum.drugs;

public enum InteractionSeverity {

	MILD,

	MODERATE,

	SEVERE,

	;

	InteractionSeverity() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
