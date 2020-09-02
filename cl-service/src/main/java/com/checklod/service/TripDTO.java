package com.checklod.service;

import lombok.Data;

@Data
public class TripDTO {

	private String tripId;
	private String invoiceId;
	private String startDate;
	private String goingStatus;
	private float proveTemp;
	private float maxTemp;
	private float minTemp;
	private String driverName;
	private String phoneNo;
	private String vehicleNo;
	private String loggerAlias;
}
