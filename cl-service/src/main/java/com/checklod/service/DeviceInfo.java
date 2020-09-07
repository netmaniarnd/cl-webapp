package com.checklod.service;

import lombok.Data;

@Data
public class DeviceInfo {

	private String loggerId;
	private int loggingIntervalSec;
	private String loggingMode;
	private String firmware;
	private String startMode;
	private String currentStatus;
	private String loggerType;
	private String startDelay;
}
