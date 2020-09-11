package com.checklod.api_repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApiRepositoryTest {

	private ApiRepository apiRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		apiRepository = new ApiRepositoryImpl();
	}

	@Test
	void testGetVehicleSummary() {
		assertNotNull(apiRepository);
		List<VehicleSummary> listVehicle = apiRepository.getVehicleSummary();
		assertNotNull(listVehicle);
		System.out.println(listVehicle.size());
	}

	@Test
	void testGetRunawayList() {
		assertNotNull(apiRepository);
		List<Long> listVehicle = apiRepository.getRunawayList();
		assertNotNull(listVehicle);
		System.out.println(listVehicle.size());
	}

}
