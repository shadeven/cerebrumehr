package com.oreon.cerebrum.unusualoccurences;

public enum TreatmentCategory {

	Radiation,

	Chemotherapy,

	Anesthesia,

	Surgery,

	Other,

	;

	TreatmentCategory() {
	}

	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
