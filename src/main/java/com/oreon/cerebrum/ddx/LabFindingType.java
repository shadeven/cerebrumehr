package com.oreon.cerebrum.ddx;

public enum LabFindingType {

	ELEVATED,

	DECREASED,

	NEGATIVE,

	POSITIVE,

	FALSE_POSITIVE,

	;

	LabFindingType() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
