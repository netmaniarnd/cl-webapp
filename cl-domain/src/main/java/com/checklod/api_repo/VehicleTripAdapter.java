package com.checklod.api_repo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class VehicleTripAdapter {

	private String tripId;
	private String checkin;
	private String goingStatus;
	private String driverName;
	private String phoneNo;
	private String vehicleNo;
	
	public VehicleTripVO convert() {
		VehicleTripVO row = new VehicleTripVO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(this.getCheckin(), formatter);
		row.setCheckin(dateTime);
		row.setTripId(Long.parseLong(this.getTripId()));
		row.setGoingStatus(this.getGoingStatus());
		row.setDriverName(this.getDriverName());
		row.setPhoneNo(this.getPhoneNo());
		row.setVehicleNo(this.getVehicleNo());
		return row;
	}
}
