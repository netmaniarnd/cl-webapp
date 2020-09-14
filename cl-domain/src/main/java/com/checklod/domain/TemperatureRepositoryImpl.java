package com.checklod.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TemperatureRepositoryImpl implements TemperatureRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;

	//Stream<Author> authors = em.createQuery("SELECT a FROM Author a", Author.class).getResultStream();
	
	@Override
	public List<TemperatureLog> findByTripId(long tripId) {
		// TODO Auto-generated method stub
		Stream<TemperatureLog> resultList = entityManager.createQuery("select t from TemperatureLog t where t.tripSegment.tripSegmentId.tripId = ?1",
				TemperatureLog.class).setParameter(1, tripId).getResultStream();
		//List<Object[]> resultList = qry.getResultList();
		if(resultList == null) return null;
		//
		//resultList.forEach(item -> {
		//	System.out.println(item.toString());
		//});
		return resultList.collect(Collectors.toList());
	}

}
