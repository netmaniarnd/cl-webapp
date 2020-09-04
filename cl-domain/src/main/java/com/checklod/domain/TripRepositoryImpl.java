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
		int limit = 1;
		List<TripSegment> resultList = entityManager.createQuery("select ts from TripSegment ts where ts.tripSegmentId.tripId = ?1 ORDER BY ts.checkout DESC",
				TripSegment.class).setParameter(1, tripId).setMaxResults(limit).getResultList();
		if(resultList == null || resultList.isEmpty()) return null;
		//
		return Optional.of(resultList.get(0));
	}

	@Override
	public List<Trip> findByLastOneMonth() {
		int limit = 100;
		// TODO dev:10, read:31
		List<Trip> resultList = entityManager.createQuery("select t from Trip t where datediff(curdate(),t.createdAt)<=10 ORDER BY t.id DESC",
				Trip.class).setMaxResults(limit).getResultList();
		if(resultList == null || resultList.isEmpty()) return null;
		return resultList;
	}

}
