package com.oreon.cerebrum.web.action.charts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jboss.seam.annotations.Name;
import org.joda.time.DateTime;

import com.oreon.cerebrum.charts.ChartItem;
import com.oreon.cerebrum.charts.ChartProcedure;
import com.oreon.cerebrum.patient.Patient;

//@Scope(ScopeType.CONVERSATION)
@Name("appliedChartAction")
public class AppliedChartAction extends AppliedChartActionBase
		implements
			java.io.Serializable {
	
	
	private static final int NUMBER_OF_CHART_ITEMS = 5;
	
	
	
	@Override
	protected void postSave() {
		
		super.postSave();
		createProcedures();
	}
	
	

	/**
	 * Creates procedures and adds them to the patient for the given chart
	 */
	public void createProcedures(){
	//	Date beginDate = appliedChart.getDateCreated();
		
		List<ChartProcedure> proceduresOfType = new ArrayList<ChartProcedure>();

		Set<ChartItem> items = instance.getChart().getChartItems();
		
		Patient patient = instance.getPatient();
		
		for (ChartItem chartItem : items) {
			
			int duration = chartItem.getDuration();
			
			System.out.println(" applying " + chartItem.getName());
			
			String query = "Select count(*) from ChartProcedure cp where cp.patient = ?1 and cp.chartItem = ?2 and cp.datePerformed is not null ";
			
			Long count = executeSingleResultQuery(query, patient, chartItem);
			int newToCreate = NUMBER_OF_CHART_ITEMS - count.intValue();
			
			System.out.println("there are nwe procedures to create - " + newToCreate);

			for (int i = 0; i < newToCreate; i++) {
				ChartProcedure procedure = new ChartProcedure();
				DateTime dt = new DateTime();
				procedure.setDueDate(dt.plusMinutes((int) (duration * i
						* chartItem.getFrequencyPeriod().getValue())).toDate());
				
				procedure.setPatient(getInstance().getPatient());
				
				procedure.setChartItem(chartItem);
				proceduresOfType.add(procedure);
				
				entityManager.persist(procedure);
			}
			
		}
		
	}

}
