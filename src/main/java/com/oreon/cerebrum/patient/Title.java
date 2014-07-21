package com.oreon.cerebrum.patient;

public enum Title {

	Mr,

	Mrs,

	Ms,

	Dr,

	;

	Title() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
