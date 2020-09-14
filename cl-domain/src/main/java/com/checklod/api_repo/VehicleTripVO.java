package com.checklod.api_repo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VehicleTripVO {

	private long tripId;
	private LocalDateTime checkin;
	private String goingStatus;
	private String driverName;
	private String phoneNo;
	private String vehicleNo;
}
