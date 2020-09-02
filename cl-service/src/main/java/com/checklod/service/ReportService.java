package com.checklod.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ReportService {

	public List<TripDTO> listAllTrips();

	public TripDetailDTO findTrip(long id);
}
