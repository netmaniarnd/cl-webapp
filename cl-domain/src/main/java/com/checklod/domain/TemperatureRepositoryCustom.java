package com.checklod.domain;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepositoryCustom {

	public List<TemperatureLog> findByTripId(long tripId);
}
