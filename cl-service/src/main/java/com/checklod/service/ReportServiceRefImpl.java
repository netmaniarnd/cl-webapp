package com.checklod.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checklod.domain.Trip;
import com.checklod.domain.TripRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportServiceRefImpl implements ReportService {

	@Autowired
	private TripRepository tripRepository;
	
	@Override
	public List<TripDTO> listAllTrips() {
		// TODO Auto-generated method stub
		Iterable<Trip> trips = tripRepository.findAll();
		log.info("trips {}", trips);
		return null;
	}

	@Override
	public TripDetailDTO findTrip(long id) {
		Optional<Trip> trip = tripRepository.findById( id);
		log.info("trip {}", trip.get());
		TripDetailDTO tripDetailDTO = new TripDetailDTO();
		tripDetailDTO.setTripId(Long.toString(trip.get().getId()));
		tripDetailDTO.setInvoiceId(trip.get().getInvoiceNo());
		return tripDetailDTO;
	}
}
