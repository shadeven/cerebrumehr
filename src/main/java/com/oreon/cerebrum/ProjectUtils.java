package com.oreon.cerebrum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.drools.util.StringUtils;

import com.oreon.cerebrum.encounter.EncounterBase;
import com.oreon.cerebrum.encounter.PrescribedTest;
import com.oreon.cerebrum.prescription.PrescriptionBase;
import com.oreon.cerebrum.prescription.PrescriptionItem;

public class ProjectUtils {
	
	public static String getPrescripitonItems(PrescriptionBase prescription){
		Set<PrescriptionItem> items = prescription.getPrescriptionItems();
		StringBuilder builder = new StringBuilder();
		for (PrescriptionItem prescriptionItem : items) {
			builder.append(prescriptionItem.getDrug().getName() + " " + prescriptionItem.getStrength() + " " +  prescriptionItem.getFrequency().getName() + "<br/>");
		}
		System.out.println("ret " + builder.toString());
		return builder.toString();
	}

	public static String getTests(EncounterBase encounter) {
		StringBuilder builder = new StringBuilder();
		for (PrescribedTest prescribedTest : encounter.getPrescribedTests()){
			builder.append(prescribedTest.getDxTest().getName());
		}
		return builder.toString();
	}
	
	
	public static List<String> getListFromCommaDeleimtedString(String arg) {
		String[] arr = arg.split(",");
		int i = 0;
		for (String string : arr) {
			string = string.trim();
			arr[i++] = string;
		}
		return Arrays.asList(arr);
	}
	
	/** Returns a list containing lists of string that are in the format {a, b,c}{d, e, f} - the sublists are
	 * comma delimited while the top level is {} delimited 
	 * @param arg
	 * @return
	 */
	public static List<List<String>> getListOfLists(String arg){
		List<List<String>> list = new ArrayList<List<String>>();
		String[] arr = arg.split("\\{|\\}| }");
		
		for (String string : arr) {
			if(!StringUtils.isEmpty(string))
			list.add(getListFromCommaDeleimtedString(string));
		}
		return list;
	}
	
	public static void main(String[] args) {
		List<List<String>>  lst = getListOfLists("{a,b}  {c,d}");
		System.out.println(lst.size());
		for (List<String> list : lst) {
			for (String string : list) {
				System.out.print(string);
			}
			System.out.println("-----");
		}
	}

}
