package com.checklod.domain;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class TripRepositoryImpl implements TripRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Optional<TripSegment> findGoingLatestByLogger(String loggerId) {
		int limit = 1;
		List<TripSegment> resultList = entityManager.createQuery("select ts from TripSegment ts where ts.loggerId = ?1 ORDER BY ts.checkout DESC",
				TripSegment.class).setParameter(1, loggerId).setMaxResults(limit).getResultList();
		if(resultList == null || resultList.isEmpty()) return null;
		//
		return Optional.of(resultList.get(0));
	}

	@Override
	public Optional<TripSegment> findGoingLatestByTripId(long tripId) {
		// TODO Auto-generated method stub
		int limit = 1;
		List<TripSegment> resultList = entityManager.createQuery("select ts from TripSegment ts where ts.tripSegmentId.tripId = ?1 ORDER BY ts.checkout DESC",
				TripSegment.class).setParameter(1, tripId).setMaxResults(limit).getResultList();
		if(resultList == null || resultList.isEmpty()) return null;
		//
		return Optional.of(resultList.get(0));
	}

}
