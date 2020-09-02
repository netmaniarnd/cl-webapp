package com.checklod.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checklod.domain.TemperatureLog;
import com.checklod.domain.TemperatureRepository;
import com.checklod.domain.Trip;
import com.checklod.domain.TripRepository;
import com.checklod.domain.TripSegment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportServiceRefImpl implements ReportService {

	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private TemperatureRepository temperatureRepository;
	
	@Override
	public List<TripDTO> listAllTrips() {
		// TODO Auto-generated method stub
		Iterable<Trip> trips = tripRepository.findAll();
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
		});
		return null;
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
