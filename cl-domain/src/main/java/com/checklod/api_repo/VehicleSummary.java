package com.checklod.api_repo;

import lombok.Data;

@Data
public class VehicleSummary {

	private String name;
	private int total;
	private int going;
	private int arrived;
	private int signed;
	private int reported;
	private int runAway;
}
