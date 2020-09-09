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
	
	public DeviceInfo(String loggerId) {
		this.loggerId = loggerId;
		this.loggingIntervalSec = 300;
		this.loggingMode = "off";
		this.firmware = "CheckLOD";
		this.startMode = "Button";
		this.currentStatus = "finish";
		this.loggerType = "BLE Logger";
		this.startDelay = "off";
	}
}
