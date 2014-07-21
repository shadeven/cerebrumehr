package com.oreon.cerebrum.web.action.ddx;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.ddx.DifferentialDx;
import com.oreon.cerebrum.ddx.Finding;
import com.oreon.cerebrum.ddx.PatientFinding;

//@Scope(ScopeType.CONVERSATION)
@Name("patientDiffDxAction")
public class PatientDiffDxAction extends PatientDiffDxActionBase implements
		java.io.Serializable {

	final Map<String, Integer> map = new HashMap<String, Integer>();
	
	List<String> differentials = new ArrayList<String>();

	public List<String> getDifferentials() {
		return differentials;
	}

	public void setDifferentials(List<String> differentials) {
		this.differentials = differentials;
	}

	public String addFinding(Finding finding) {

		finding.getDifferentialDxs();
		StringBuilder warnings = new StringBuilder();

		List<PatientFinding> items = getListPatientFindings();

		// TODO: search for patinetFinding.finding
		// if (items.contains(finding))
		// return "nothing";

		// for (PatientFinding patientFinding : items) {
		List<DifferentialDx> dxs = finding.getListDifferentialDxs();
		
		for (DifferentialDx differentialDx : dxs) {
			Integer value = map.get(differentialDx.getName());
			if( value == null){
				map.put(differentialDx.getName(), new Integer(1));
			}else{
				map.put(differentialDx.getName(), value + 1);
			}
			
		}
		// }

		updateDiff();
		
		return "success";
	}
	
	
	@Override
	public void deletePatientFindings(int index) {
		// TODO Auto-generated method stub
		Finding finding = getListPatientFindings().get(index).getFinding();
		
		super.deletePatientFindings(index);
		
		List<DifferentialDx> dxs = finding.getListDifferentialDxs();

		for (DifferentialDx differentialDx : dxs) {
			Integer value = map.get(differentialDx.getName());
			value--;
			if (value == 0) {
				map.remove(differentialDx.getName());
			} else {
				map.put(differentialDx.getName(), value);
			}
		}
		
		updateDiff();
	}

	// @Override
	public void setNewFinding(Finding finding) {
		addFinding(finding);
		// this.finding = finding;
	}
	
	public List<String> updateDiff(){
		differentials = new ArrayList<String>();
		ValueComparator comp = new ValueComparator(map);
		TreeMap<String, Integer> sorted = new TreeMap();
		sorted.putAll(map);
		
		Set<String> keys = map.keySet();
		
		for (String key : keys) {
			differentials.add(key + " " + map.get(key));
			//System.out.println(key + " " + map.get(key));
		}
		
		return differentials;
	
	}

	class ValueComparator implements Comparator {

		Map base;

		public ValueComparator(Map base) {
			this.base = base;
		}

		public int compare(Object a, Object b) {
			
			Integer val1 = ((Integer) base.get(a));
			Integer val2 = ((Integer) base.get(b));
			
			//descending compare
			return val2.compareTo(val1);
			
		}
	}

}
