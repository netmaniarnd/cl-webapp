package com.checklod.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long>, TripRepositoryCustom {
	
	public Optional<TripSegment> findGoingLatestByLogger(String loggerId);
	public Optional<TripSegment> findGoingLatestByTripId(long tripId);
}
