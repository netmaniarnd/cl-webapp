package com.checklod.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checklod.domain.Logger;
import com.checklod.domain.LoggerRepository;
import com.checklod.domain.TemperatureLog;
import com.checklod.domain.TemperatureRepository;
import com.checklod.domain.Trip;
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
			if(tripSegment.isPresent()) log.debug("tripsegment info {}", tripSegment.get().toString());
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
			tripDTO.setStartDate(tripSegment.get().getCheckin().format(formatter));
			GoingStatus goingStatus = GoingStatus.get(trip.getGoingStatus());
			tripDTO.setGoingStatus(goingStatus.getLabel());
			tripDTO.setProveTemp(tripTemps.get(0).getIntTemp());
			float maxTemp = 0.0f;
			tripDTO.setMaxTemp(maxTemp );
			float minTemp = 0.0f;
			tripDTO.setMinTemp(minTemp );
			tripDTO.setDriverName(tripSegment.get().getDriverName());
			tripDTO.setPhoneNo(tripSegment.get().getPhoneNo());
			tripDTO.setVehicleNo(tripSegment.get().getVehicleNo());
			Optional<Logger> logger = loggerRepository.findById(tripSegment.get().getLoggerId());
			tripDTO.setLoggerAlias(logger.get().getAlias());
			//
			log.debug("tripDTO {}", tripDTO.toString());
			listTrip.add(tripDTO);
		});
		return listTrip;
	}

	@Override
	public TripDetailDTO findTrip(long id) {
		Optional<Trip> trip = tripRepository.findById( id);
		log.debug("trip {}", trip.get());
		Optional<TripSegment> tripSegment = tripRepository.findGoingLatestByTripId(id);
		log.debug("tripsegment info {}", tripSegment.get());
		List<TemperatureLog> tripTemps = temperatureRepository.findByTripId(id);
		log.debug("trip temp size {}", tripTemps.size());
		TripDetailDTO tripDetailDTO = new TripDetailDTO();
		tripDetailDTO.setTripId(Long.toString(trip.get().getId()));
		tripDetailDTO.setInvoiceId(trip.get().getInvoiceNo());
		return tripDetailDTO;
	}
}
