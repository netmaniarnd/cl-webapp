package com.checklod.service;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReportSummary {

	private float maxTemp;
	private float minTemp;
	private int alarmCount;
	private int batteryPower;
	private LocalDate pointStart;
	private LocalDate pointEnd;
}
