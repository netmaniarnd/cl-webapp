package com.checklod.api_repo;

import lombok.Data;

@Data
public class VehicleSummaryAdapter {

	private String vehicleNo;
	private int total;
	private int state2;
	private int state3;
	private int state4;
	private int state5;
	private int extra;
	
	public VehicleSummary convert() {
		VehicleSummary row = new VehicleSummary();
		row.setName(this.getVehicleNo());
		row.setTotal(this.getTotal());
		row.setGoing(this.getState2());
		row.setArrived(this.getState3());
		row.setSigned(this.getState4());
		row.setReported(this.getState5());
		 
		return row;
	}
}
