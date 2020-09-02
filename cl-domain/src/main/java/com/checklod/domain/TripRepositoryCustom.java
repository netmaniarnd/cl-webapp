package com.checklod.domain;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface TripRepositoryCustom {

	public Optional<TripSegment> findGoingLatestByLogger(String loggerId);
	public Optional<TripSegment> findGoingLatestByTripId(long tripId);
}
