package com.oreon.cerebrum.patient;

public enum DischargeCode {

	REGULAR,

	DECEASED,

	REFERRED,

	;

	DischargeCode() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
