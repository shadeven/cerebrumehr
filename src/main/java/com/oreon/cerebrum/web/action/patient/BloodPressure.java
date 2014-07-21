package com.oreon.cerebrum.web.action.patient;

import java.util.Date;

public class BloodPressure{
		public BloodPressure(Date date, int systolic, int diastolic) {
			super();
			this.date = date;
			this.systolic = systolic;
			this.diastolic = diastolic;
		}
		
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getSystolic() {
			return systolic;
		}
		public void setSystolic(int systolic) {
			this.systolic = systolic;
		}
		public int getDiastolic() {
			return diastolic;
		}
		public void setDiastolic(int diastolic) {
			this.diastolic = diastolic;
		}

		Date date;
		int systolic;
		int diastolic;
		
	}