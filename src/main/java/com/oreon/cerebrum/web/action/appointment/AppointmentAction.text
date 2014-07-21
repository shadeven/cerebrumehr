
	
package com.oreon.cerebrum.web.action.appointment;
	

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.criteria.predicate.IsEmptyPredicate;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.oreon.cerebrum.appointment.Appointment;

	
//@Scope(ScopeType.CONVERSATION)
@Name("appointmentAction")
public class AppointmentAction extends AppointmentActionBase implements java.io.Serializable{
	
	public static  final int APPT_DURATION = 15;
	
	@Override
	public String doSave() {
		
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(instance.getStart());
		gregorianCalendar.add(Calendar.MINUTE, APPT_DURATION * instance.getUnits() );
		instance.setEnd(gregorianCalendar.getTime() );
		
		if(checkConflict()){
			addErrorMessage("This appoinment conflicts with another appointment");
			return "false";
		}
		
		return super.doSave();
	}
	
	public boolean checkConflict(){
		String qry = "select a from Appointment a where ((a.start <= ?1 and a.end >  ?1) or  (a.start < ?2 and a.end >=  ?2))  and a.physician = ?3 ";
		
		List<Appointment> appts = executeQuery(qry, instance.getStart(), instance.getEnd(), instance.getPhysician());
		if(!appts.isEmpty()){
			return true;
		}
		
		return false;
	}
	
	/** 
	 * @return
	 */
	public List<Appointment> findNextAvailable(){
		return null;
	}
	
}
	