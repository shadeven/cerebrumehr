package com.oreon.cerebrum.web.action.patient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.primefaces.event.SelectEvent;
import org.witchcraft.seam.action.UserUtilAction;

import com.oreon.cerebrum.charts.AppliedChart;
import com.oreon.cerebrum.charts.ChartProcedure;
import com.oreon.cerebrum.encounter.Encounter;
import com.oreon.cerebrum.patient.Patient;
import com.oreon.cerebrum.patient.TrackedVital;
import com.oreon.cerebrum.patient.VitalValue;

//@Scope(ScopeType.CONVERSATION)
@Name("patientAction")
public class PatientAction extends PatientActionBase implements
		java.io.Serializable {

	private ArrayList<BloodPressure> bpList;
	
	@In(create=true)
	private UserUtilAction userUtilAction;

	public PatientAction() {
		// TODO Auto-generated constructor stub

	}
	
	public void handlePatientSelect(SelectEvent se){
		Patient patient = (Patient)se.getObject();
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected patient " + patient.getDisplayName(), null);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		setInstance((Patient) se.getObject());
		userUtilAction.setCurrentPatient(getInstance());
	}

	public String getPatientInfo(){
		
		if(userUtilAction.getCurrentPatient() == null)
			return "No Patient Selected";
		else
			return userUtilAction.getCurrentPatient().getDetailedInfo();
		
	}
	
	
	@Override
	public Patient getInstance() {
		// TODO Auto-generated method stub
		Patient pt =  super.getInstance();
		if(pt.isNew()){
			pt = userUtilAction.getCurrentPatient();
		}
		
		return pt;
	}

	/**
	 * Should calculate upcoming chart procedure dates
	 */
	public List<List<ChartProcedure>> viewUpcomingChartProcedures() {

		Set<AppliedChart> charts = instance.getAppliedCharts();

		List<List<ChartProcedure>> procedures = new ArrayList<List<ChartProcedure>>();

		/*
		for (AppliedChart appliedChart : charts) {
			Date beginDate = appliedChart.getDateCreated();
			
			List<ChartProcedure> proceduresOfType = new ArrayList<ChartProcedure>();

			Set<ChartItem> items = appliedChart.getChart().getChartItems();
			
			
			for (ChartItem chartItem : items) {
				
				int duration = chartItem.getDuration();

				for (int i = 0; i < 5; i++) {
					ChartProcedure procedure = new ChartProcedure();
					DateTime dt = new DateTime();
					procedure.setDueDate(dt.plusMinutes((int) (duration * i
							* chartItem.getFrequencyPeriod().getValue())).toDate());
					procedure.setPatient(instance);
					
					procedure.setChartItem(chartItem);
					proceduresOfType.add(procedure);
				}
			}
			procedures.add(proceduresOfType);
		}
		*/
		return procedures;
	}

	public void initBloodPressureValues() {

		if (instance == null)
			load(0L);

		bpList = new ArrayList<BloodPressure>();

		Set<Encounter> encounters = getInstance().getEncounters();
		for (Encounter encounter : encounters) {
			if (encounter.getVitals() != null
					&& encounter.getVitals().getSysBP() != null
					&& encounter.getVitals().getDiasBP() != null)
				bpList.add(new BloodPressure(encounter.getDateCreated(),
						encounter.getVitals().getSysBP(), encounter.getVitals()
								.getDiasBP()));
		}
	}

	public List<BloodPressure> getBloodPressureValues() {
		if (bpList == null)
			initBloodPressureValues();
		return bpList;
	}
	
	
	List<List<VitalValue>> listVitals;

	public List<List<VitalValue>> getTrackedVals() {
		
		if(listVitals != null)
			return listVitals;
		
		listVitals = new ArrayList<List<VitalValue>>();
		Map<TrackedVital, List<VitalValue>> mapVitals = new HashMap<TrackedVital, List<VitalValue>>();
		
		VitalValueListQuery vitalValueListQuery = (VitalValueListQuery) Component.getInstance("vitalValueList");

		List<VitalValue> vitals = vitalValueListQuery.getAllVitalValuesByPatient(getInstance());
		for (VitalValue vitalValue : vitals) {
			if (!mapVitals.containsKey(vitalValue.getTrackedVital())) {
				mapVitals.put(vitalValue.getTrackedVital(),
						new ArrayList<VitalValue>());
			}
			mapVitals.get(vitalValue.getTrackedVital()).add(vitalValue);
		}
		Set<TrackedVital> tvs = mapVitals.keySet();
		for (TrackedVital trackedVital : tvs) {
			listVitals.add(mapVitals.get(trackedVital));
		}
		return listVitals;
	}
	
	/* (non-Javadoc)
	 * @see org.witchcraft.seam.action.BaseAction#onRowSelect(org.primefaces.event.SelectEvent)
	 */
	@Override
	public void onRowSelect(SelectEvent event) throws Exception {
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/admin/entities/patient/patient/editPatient.seam?patientId="
								+ getPatientId() + "&conversationPropagation=none");
		
	}
	
	class DateComparator implements Comparator<BloodPressure> {

		//@Override
		public int compare(BloodPressure bp1, BloodPressure bp2) {

			if (bp1.getDate().getTime() > bp2.getDate().getTime())
				return 1;
			return 0;
		}

	}

}
