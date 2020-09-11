package com.checklod.service;

import java.util.List;

import com.checklod.api_repo.VehicleSummary;

import lombok.Data;

@Data
public class FrontDTO {

	private FrontSummary frontSummary;
	private List<VehicleSummary> vehicleSummary;
	private VehicleSummary dailySummary;
	private List<Long> runAways;
}
