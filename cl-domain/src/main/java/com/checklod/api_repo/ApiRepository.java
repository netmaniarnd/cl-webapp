package com.checklod.api_repo;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface ApiRepository {

	public List<VehicleSummary> getVehicleSummary();

	public List<Long> getRunawayList();

	public List<VehicleTripVO> getVehicleTrips(String vehicleNo);
}
