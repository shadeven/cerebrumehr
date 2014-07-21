package com.oreon.cerebrum.prescription;

public enum Route {

	PO,

	IV,

	IM,

	SC,

	TOPICAL,

	;

	Route() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
