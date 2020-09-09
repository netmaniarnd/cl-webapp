package com.checklod.service;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReportSummary {

	private float maxTemp;
	private float minTemp;
	private int alarmCount;
	private int batteryPower;
	private LocalDateTime pointStart;
	private LocalDateTime pointEnd;
}
