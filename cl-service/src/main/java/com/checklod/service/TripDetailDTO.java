package com.checklod.service;

import java.util.List;

import lombok.Data;

@Data
public class TripDetailDTO {

	private String tripId;
	private String invoiceId;
	private ReportSummary reportSummary;
	private DeviceInfo devieInfo;
	private String driverName;
	private String phoneNo;
	private String vehicleNo;
	private String loggerAlias;
	private List<PointTemp> listTemp;
	private List<MediaInfo> listMedia;
}
