package com.oreon.cerebrum.web.action.appointment;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;
import org.joda.time.DateTime;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.oreon.cerebrum.appointment.Appointment;
import com.oreon.cerebrum.employee.Physician;

@Scope(ScopeType.SESSION)
@Name("appointmentAction")
public class AppointmentAction extends AppointmentActionBase implements
		java.io.Serializable {

	private ScheduleModel eventModel;
	
	private Physician currentPhysician;

	private ScheduleEvent event = new DefaultScheduleEvent();

	public ScheduleModel getEventModel() {
		if (eventModel == null) {
			updateEvents();
		}

		return eventModel;
	}

	public void updateEvents() {
		eventModel = new DefaultScheduleModel();
		AppointmentListQuery appointmentListQuery = (AppointmentListQuery) Component
				.getInstance("appointmentList");

		if(currentPhysician == null)
			return;
		List<Appointment> appts = appointmentListQuery.getAppointmentsByPhysician(currentPhysician);
		
		for (Appointment appointment : appts) {
			addAppointmentToSchedule(appointment, null);
		}
	}

	private void addAppointmentToSchedule(Appointment appointment,
			DefaultScheduleEvent evt) {

		if (evt == null)
			evt = new DefaultScheduleEvent("", appointment.getStart(),
					appointment.getEnd());

		evt.setTitle(appointment.getPatient().getDisplayName());
		evt.setData(appointment.getId());
		// evt.setId(appointment.getId().toString());
		eventModel.addEvent(evt);
	}

	public void addEvent(ActionEvent actionEvent) {
		save(true);
		if (event.getId() == null)
			eventModel.addEvent(event);
		else
			eventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		//clearInstance();
		event = (ScheduleEvent) selectEvent.getObject();
		load((Long) event.getData());
		// updateAppointmentFromScheduleEvent(getInstance(), (ScheduleEvent)
		// selectEvent.getObject());
	}
	 	
	
	public void cancelAppointment(){
		eventModel.deleteEvent(event);
		getEntityManager().remove(instance);
		
	}

	@Begin(join = true)
	public void onDateSelect(SelectEvent selectEvent) {
		clearInstance();

		DateTime dtStart = new DateTime((Date) selectEvent.getObject());
		System.out.println(dtStart);

		DateTime dtEnd = dtStart.plusMinutes(30);
		 
		event = new DefaultScheduleEvent("", dtStart.toDate(), dtEnd.toDate());

		getInstance().setStart(dtStart.toDate());
		if (Conversation.instance().getId() == null)
			Conversation.instance().begin(true, false);
		
	}

	@Override
	@Begin(join = true)
	public String save(boolean endConv) {
		
		//System.out.println("current conersation end "
		//		+ Conversation.instance().getId());

		DateTime dtEnd = new DateTime(getInstance().getStart());
		dtEnd = dtEnd.plusMinutes(30 * instance.getUnits());
		instance.setEnd(dtEnd.toDate());
		

		((DefaultScheduleEvent) event).setEndDate(getInstance().getEnd());

		// else
		if (!isNew())
			eventModel.updateEvent(event);
		else{
			instance.setPhysician(getCurrentPhysician());
			instance.setPatient(patientAction.getInstance());
		}

		return super.save(endConv);
	}

	@Override
	protected void postSave() {
		if (event.getData() == null)
			addAppointmentToSchedule(instance, (DefaultScheduleEvent) event);
		super.postSave();
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event moved", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		Appointment current = entityManager.find(Appointment.class,
				(Long) event.getScheduleEvent().getData());
		updateAppointmentFromScheduleEvent(current, event.getScheduleEvent());
		persist(current);
		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event resized", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void updateAppointmentFromScheduleEvent(Appointment appt,
			ScheduleEvent scheduleEvent) {
		appt.setStart(scheduleEvent.getStartDate());
		appt.setEnd(scheduleEvent.getEndDate());
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void setCurrentPhysician(Physician currentPhysician) {
		this.currentPhysician = currentPhysician;
	}

	public Physician getCurrentPhysician() {
		return currentPhysician;
	}

	public boolean getGoodToRender(){
		if(isNew())
			return  (  (patientAction.getInstance() != null && patientAction.getInstance().getId() != null   )&& currentPhysician != null );
		return true;	
	}
}