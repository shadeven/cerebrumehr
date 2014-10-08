package com.oreon.cerebrum.web.action.admission;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.witchcraft.exceptions.BusinessException;

import com.oreon.cerebrum.admission.BedStay;
import com.oreon.cerebrum.billing.Invoice;
import com.oreon.cerebrum.billing.InvoiceItem;
import com.oreon.cerebrum.billing.Service;
import com.oreon.cerebrum.facility.Bed;
import com.oreon.cerebrum.facility.Room;
import com.oreon.cerebrum.facility.RoomType;
import com.oreon.cerebrum.facility.Ward;
import com.oreon.cerebrum.patient.Patient;
import com.oreon.cerebrum.web.action.billing.ServiceAction;
import com.oreon.cerebrum.web.action.facility.BedAction;

@Scope(ScopeType.CONVERSATION)
@Name("admissionAction")
public class AdmissionAction extends AdmissionActionBase implements
		java.io.Serializable {

	private Ward ward;

	private RoomType roomType;

	private Bed preferredBed;

	private Bed nonPreferredBed;

	private Integer preferredBedsCount = 0;
	private Integer nonPreferredBedsCount = 0;

	@In(create = true)
	ServiceAction serviceAction;


	@In(create = true)
	BedAction bedAction;

	static final String qryPref = "Select r from Room r where r.ward.id = ? and r.roomType = ? and ( select count(b) from Bed b where b.patient is null and b.room = r)  > 0   ";
	static final String qryNonPref = "Select r from Room r where r.ward.id = ? and r.roomType != ? and ( select count(b) from Bed b where b.patient is null and b.room = r)  > 0   ";

	static String qryAll = "Select r from Room r where r.ward.id = ? and ( select count(b) from Bed b where b.patient is null and b.room = r)  > 0   ";

	static final String qryAvailableBedsForRoom = "select b from Bed b where b.patient is null and b.room = ?";

	public Integer getNonPreferredBedsCount() {
		return getNonPreferredBedsList().size();
	}

	public Integer getpreferredBedsCount() {
		return getPrefferedBeds().size();
	}

	public void setNonPreferredBedsCount(Integer nonPreferredBedsCount) {
		this.nonPreferredBedsCount = nonPreferredBedsCount;
	}

	@In
	Conversation conversation;

	public Bed getPreferredBed() {
		return preferredBed;
	}

	public void setPreferredBed(Bed preferredBed) {
		this.preferredBed = preferredBed;
	}

	public void setPreferredBedsCount(Integer preferredBedsCount) {
		this.preferredBedsCount = preferredBedsCount;
	}

	public Bed getNonPreferredBed() {
		return nonPreferredBed;
	}

	public void setNonPreferredBed(Bed nonPreferredBed) {
		this.nonPreferredBed = nonPreferredBed;
	}

	public List<Room> getPreferredRoomsList() {
		if (ward == null || roomType == null)
			return new ArrayList<Room>();
		// String nativeQry =
		// "SELECT * FROM Room r WHERE r.ward_id = ? AND ( SELECT COUNT(*) FROM Bed b WHERE b.patient_id IS NULL AND b.room_id = r.id  > 0 )";
		// System.out.println("looking for " + ward.getName() + " " +
		// roomType.getName());
		List<Room> rooms = executeQuery(qryPref, ward.getId(), roomType);

		return rooms;
	}

	@Begin(join = true)
	public List<Room> getNonPreferredRoomsList() {
		if (ward == null)
			return new ArrayList<Room>();

		if (roomType != null)
			return executeQuery(qryNonPref, ward.getId(), roomType);

		return executeQuery(qryAll, ward.getId());
	}

	@Begin(join = true)
	public List<Bed> getPrefferedBeds() {

		List<Room> rooms = getPreferredRoomsList();
		return getAvailableBeds(rooms);
	}

	public List<Bed> getNonPreferredBedsList() {
		List<Room> rooms = getNonPreferredRoomsList();
		return getAvailableBeds(rooms);
	}

	/**
	 * Get all available beds for given rooms
	 * 
	 * @param rooms
	 * @return
	 */
	private List<Bed> getAvailableBeds(List<Room> rooms) {
		List<Bed> beds = new ArrayList<Bed>();
		for (Room room : rooms) {
			Hibernate.initialize(room.getBeds());
			List<Bed> availBeds = executeQuery(qryAvailableBedsForRoom, room);
			beds.addAll(availBeds);
		}

		setNonPreferredBedsCount(beds.size());
		return beds;
	}

	@Override
	public void updateComposedAssociations() {
		// TODO Auto-generated method stub
		// super.updateComposedAssociations();
	}

	// @Override
	public boolean isDischargeEnabled() {
		return instance.getDischargeDate() == null;
	}

	// @Override
	public boolean isTransferEnabled() {
		return instance.getDischargeDate() == null;
	}

	private void createBedStay() {
		
		setupBedAndPatient(getCurrentBed());
		Bed	bed = getCurrentPatient().getBed();


		BedStay bedStay = new BedStay();
		bedStay.setAdmission(instance);
		bedStay.setBed(bed);
		bedStay.setFromDate(new Date());
		instance.addBedStay(bedStay);
	}

	private void setupBedAndPatient(Bed bed) {
		Patient pt = getCurrentPatient();
		if(pt == null )
			pt = instance.getPatient();
		bed.setPatient(pt);
		pt.setBed(bed);
	}
	
	private void clearBedAndPatient() {
		Patient pt = getCurrentPatient();
		if(pt.getBed() != null)
			pt.getBed().setPatient(null);
		pt.setBed(null);
	}

	private Patient getCurrentPatient() {
		Patient pt = patientAction.getInstance();
		if(pt == null )
			pt = instance.getPatient();
		if(pt == null )
			throw new BusinessException("No patient available");
		return pt;
	}


	private void updateBedStayDate() {
		for (BedStay bedStay : instance.getBedStays()) {
			if (bedStay.getToDate() == null)
				bedStay.setToDate(new Date());
			//bedStay.getBed().setPatient(null);
		}
	}

	@Override
	public void transfer() {
		//update the previous bed end date
		updateBedStayDate();
		
		clearBedAndPatient();
		setupBedAndPatient(getCurrentBed());
		//create a new bed stay
		createBedStay();
		save(true);

		listBedStays.clear();
		listBedStays.addAll(getInstance().getBedStays());

		addInfoMessage("Successfully transferred to bed " + getCurrentPatient().getBed());
	}

	//@Override
	@End
	public String admit(boolean end ) {
		
		Patient patient = patientAction.getInstance();
		
		if( patient == null || patient.getId() == null )
			throw new BusinessException("Please Select a Patient");
		
		if(patient.getBed() != null)
			throw new BusinessException("Patient "  + patient.getLastName() + patient.getFirstName() + " is already  admitted to " + patient.getBed().getDisplayName());
		
		createBedStay();
		
		return super.save(end);
	}

	
	// @Override
	public void discharge(String dischargeNote,
			com.oreon.cerebrum.patient.DischargeCode dischargeCode) {

		updateBedStayDate();
		instance.setDischargeDate(new Date());
		
		clearBedAndPatient();
		
		super.save(true);
		addInfoMessage("Successfully Discharged patient");

	}

	public void discharge() {
		discharge(instance.getDischargeNote(), instance.getDischargeCode());
	}


	
	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public Ward getWard() {
		return ward;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	
	public void updateSelectedBed() {
		// this.selectedBed = this.bed;
	}
	
	private Bed getCurrentBed() {
		Bed bed = preferredBed != null ? preferredBed : nonPreferredBed;

		if (bed == null)
			throw new BusinessException("Please Select a Bed");
		
		return bed;
	}
	

	public List<Ward> getWardList() {
		/*
		if (instance.getPatient() == null
				|| instance.getPatient().getGender() == null)
			return new ArrayList<Ward>();
			
		*/
		// System.out.println(instance.getPatient().getGender());
		String qry = "Select e from Ward e where e.gender = ?  or e.gender is null ";
		if(patientAction.getInstance() != null && patientAction.getInstance().getId() != null){
			getInstance().setPatient(patientAction.getInstance());
		}
		return executeQuery(qry, instance.getPatient().getGender());
	}

	public Invoice getAdmissionInvoice() {
		List<BedStay> stays = instance.getListBedStays();
		Invoice invoice = instance.getInvoice();

		BigDecimal total = new BigDecimal(0.0);

		for (BedStay bedStay : stays) {
			Days days = Days.daysBetween(new DateTime(bedStay.getFromDate()),
					new DateTime(bedStay.getToDate()));
			BigDecimal current = new BigDecimal(bedStay.getBed().getRoom()
					.getRoomType().getRate()).multiply(new BigDecimal(days
					.getDays()));
			InvoiceItem item = new InvoiceItem();
			item.setAppliedPrice(new BigDecimal(bedStay.getBed().getRoom()
					.getRoomType().getRate()));
			item.setUnits(days.getDays());

			Service service = createOrFindService(bedStay);

			item.setService(service);
			item.setTotal(current);
			//TODO:
			//item.setRemarks(bedStay.getFromDate() + "-" + bedStay.getToDate());
			total = total.add(current);
		}

		return invoice;
	}

	private Service createOrFindService(BedStay bedStay) {
		String serviceName = "Stay "
				+ bedStay.getBed().getRoom().getRoomType().getName();
		Service service = serviceAction.executeSingleResultQuery(
				"Select e from Service e where e.name = ?", serviceName);
		if (service == null) {
			service = new Service();
			service.setName(serviceName);
			serviceAction.persist(service);
		}
		return service;
	}

	public BigDecimal getTotalCharges() {
		List<BedStay> stays = instance.getListBedStays();
		BigDecimal total = new BigDecimal(0.0);
		for (BedStay bedStay : stays) {
			Days days = Days.daysBetween(new DateTime(bedStay.getFromDate()),
					new DateTime(bedStay.getToDate()));
			Double current = bedStay.getBed().getRoom().getRoomType().getRate()
					* (days.getDays());
			total = total.add(new BigDecimal(current));
		}

		return total;

	}
}
