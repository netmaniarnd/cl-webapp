package com.checklod.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checklod.domain.Logger;
import com.checklod.domain.LoggerRepository;
import com.checklod.domain.TemperatureLog;
import com.checklod.domain.TemperatureRepository;
import com.checklod.domain.Trip;
import com.checklod.domain.TripMedia;
import com.checklod.domain.TripRepository;
import com.checklod.domain.TripSegment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private TemperatureRepository temperatureRepository;
	
	@Autowired
	private LoggerRepository loggerRepository;
	
	@Override
	public List<TripDTO> listAllTrips() {
		// TODO Auto-generated method stub
		List<TripDTO> listTrip = new ArrayList<TripDTO>();
		Iterable<Trip> trips = tripRepository.findByLastOneMonth();
		log.debug("trips {}", trips);
		trips.forEach(trip -> {
			log.debug("trip info {}", trip);
			Optional<TripSegment> tripSegment = tripRepository.findGoingLatestByTripId(trip.getId());
			TripSegment lastSegment = tripSegment.get();
			if(tripSegment.isPresent()) log.debug("tripsegment info {}", lastSegment.toString());
			List<TemperatureLog> tripTemps = temperatureRepository.findByTripId(trip.getId());
			if(tripTemps != null) log.debug("trip temp size {}", tripTemps.size());
			//tripTemps.forEach(temp -> {
			//	log.debug("temp info {}", temp.toString());
			//});
			if(tripTemps == null || tripTemps.size() == 0) {
				log.warn("skip no segment trip");
				return;
			}
			TripDTO tripDTO = new TripDTO();
			tripDTO.setTripId(Long.toString(trip.getId()));
			tripDTO.setInvoiceId(trip.getInvoiceNo());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			tripDTO.setStartDate(lastSegment.getCheckin().format(formatter));
			GoingStatus goingStatus = GoingStatus.get(trip.getGoingStatus());
			tripDTO.setGoingStatus(goingStatus.getLabel());
			tripDTO.setProveTemp(tripTemps.get(0).getIntTemp());
			//
			Map<String, Float> tempRange = getTempRange(tripTemps);
			tripDTO.setMaxTemp(tempRange.get("max"));
			tripDTO.setMinTemp(tempRange.get("min"));

			tripDTO.setDriverName(lastSegment.getDriverName());
			tripDTO.setPhoneNo(lastSegment.getPhoneNo());
			tripDTO.setVehicleNo(lastSegment.getVehicleNo());
			String loggerId = lastSegment.getLoggerId();
			String alias = getLoggerAlias(loggerId);
			tripDTO.setLoggerAlias(alias);
			//
			//log.debug("tripDTO {}", tripDTO.toString());
			listTrip.add(tripDTO);
		});
		return listTrip;
	}

	private String getLoggerAlias(String loggerId) {
		Optional<Logger> logger = loggerRepository.findById(loggerId);
		String alias = logger.get().getAlias();
		return alias;
	}

	@Override
	public TripDetailDTO findTrip(long id) {
		Optional<Trip> trip = tripRepository.findById( id);
		log.debug("trip {}", trip.get());
		Optional<TripSegment> tripSegment = tripRepository.findGoingLatestByTripId(id);
		TripSegment lastSegment = tripSegment.get();
		log.debug("tripsegment info {}", lastSegment);
		List<TemperatureLog> tripTemps = temperatureRepository.findByTripId(id);
		log.debug("trip temp size {}", tripTemps.size());
		TripDetailDTO tripDetailDTO = new TripDetailDTO();
		tripDetailDTO.setTripId(Long.toString(trip.get().getId()));
		tripDetailDTO.setInvoiceId(trip.get().getInvoiceNo());
		//
		ReportSummary reportSummary = new ReportSummary();
		Map<String, LocalDateTime> points = getTempRunTime(tripTemps);
		reportSummary.setPointStart(points.get("start"));
		reportSummary.setPointEnd(points.get("end"));
		tripDetailDTO.setReportSummary(reportSummary );
		//
		DeviceInfo devieInfo = new DeviceInfo(lastSegment.getLoggerId());
		tripDetailDTO.setDevieInfo(devieInfo );
		tripDetailDTO.setDriverName(lastSegment.getDriverName());
		tripDetailDTO.setPhoneNo(lastSegment.getPhoneNo());
		tripDetailDTO.setVehicleNo(lastSegment.getVehicleNo());
		String alias = getLoggerAlias(lastSegment.getLoggerId());
		tripDetailDTO.setLoggerAlias(alias);
		//
		float minTemp[] = {Float.MAX_VALUE};
		float maxTemp[] = {Float.MIN_VALUE};
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		List<PointTemp> listTemp = new ArrayList<PointTemp>();
		tripTemps.forEach(item -> {
			PointTemp temp = new PointTemp();
			String point = item.getMeasuredAt().format(formatter);
			temp.setPoint(point );
			temp.setProveTemp(item.getIntTemp());
			listTemp.add(temp);
			//
			minTemp[0] = Math.min(minTemp[0], item.getIntTemp());
			maxTemp[0] = Math.max(maxTemp[0], item.getIntTemp());
		});
		tripDetailDTO.setListTemp(listTemp);
		reportSummary.setMinTemp(minTemp[0]);
		reportSummary.setMaxTemp(maxTemp[0]);
		//
		List<TripMedia> tripMedia = tripRepository.findTripMediaByTripId(id);
		List<MediaInfo> listMedia = new ArrayList<MediaInfo>();
		tripMedia.forEach(item -> {
			MediaInfo mediaItem = new MediaInfo();
			mediaItem.setImgType(item.getImgType());
			mediaItem.setImgUrl(item.getImgUrl());
			listMedia.add(mediaItem);
		});
		tripDetailDTO.setListMedia(listMedia);
		//
		log.debug("tripDetailDTO {}", tripDetailDTO.toString());
		return tripDetailDTO;
	}

	private Map<String, Float> getTempRange(List<TemperatureLog> tripTemps) {
		HashMap<String, Float> result = new HashMap<String, Float>();
		float minTemp[] = {Float.MAX_VALUE};
		float maxTemp[] = {Float.MIN_VALUE};
		tripTemps.forEach(item -> {
			minTemp[0] = Math.min(minTemp[0], item.getIntTemp());
			maxTemp[0] = Math.max(maxTemp[0], item.getIntTemp());
		});
		result.put("max", maxTemp[0]);
		result.put("min", minTemp[0]);
		return result;
	}

	private Map<String, LocalDateTime> getTempRunTime(List<TemperatureLog> tripTemps) {
		// TODO Auto-generated method stub
		LocalDateTime pointStart = null;
		LocalDateTime pointEnd = null;
		try {
			pointStart = tripTemps.get(0).getMeasuredAt();
			pointEnd = tripTemps.get(tripTemps.size()-1).getMeasuredAt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		HashMap<String, LocalDateTime> result = new HashMap<String, LocalDateTime>();
		result.put("start", pointStart);
		result.put("end", pointEnd);
		return result;
	}
}
