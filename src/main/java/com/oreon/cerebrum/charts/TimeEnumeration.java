package com.oreon.cerebrum.charts;

public enum TimeEnumeration {
	
	

	HOUR (60L),

	DAY (1440L),

	WEEK (10080L),

	MONTH (43200L),

	YEAR  (525600L),   

	;
	
	Long val;

	TimeEnumeration() {
	}
	
	TimeEnumeration(Long time) {
		this.val = time;
	}
	
	public Long getValue(){
		return val;
	}
	


	public String getName() {
		return this.toString();
	}

	public String getDisplayName() {
		return this.toString();
	}
}
