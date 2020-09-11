package com.checklod.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.checklod.api_repo.ApiRepository;
import com.checklod.api_repo.VehicleSummary;
import com.checklod.domain.LoggerRepository;
import com.checklod.domain.TemperatureRepository;
import com.checklod.domain.TripRepository;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = {com.checklod.service.ReportService.class})
//@ContextConfiguration(classes = {com.checklod.service.ReportService.class})
@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

	/*
	 * @TestConfiguration static class ReportServiceImplTestContextConfiguration {
	 * 
	 * @Bean public ReportService reportService() {
	 * System.out.println("enter reportService"); return new ReportServiceImpl(); }
	 * }
	 */

	@Mock
	private TripRepository tripRepository;
	
	@Mock
	private TemperatureRepository temperatureRepository;
	
	@Mock
	private LoggerRepository loggerRepository;
	
	@Mock
	private ApiRepository apiRepository;

	@InjectMocks
	private ReportServiceImpl reportService;
	
	@BeforeEach
	void setUp() throws Exception {
		assertNotNull(reportService);
	}

	//@Test
	void testGetFront() {
		FrontDTO frontDto = reportService.getFront();
		assertNotNull(frontDto);
	}

	//@Test
	void testGetVehicleTrips() {
		String vehicleName = "NET_05";
		List<VehicleTripDTO> result = reportService.getVehicleTrips(vehicleName );
		assertNotNull(result);
	}

	@Test
	void removeElement() {
		List<VehicleSummary> vehicleSummary = new ArrayList<VehicleSummary>();
		assertEquals(0, vehicleSummary.size());
		vehicleSummary.add(new VehicleSummary());
		assertEquals(1, vehicleSummary.size());
		vehicleSummary.add(new VehicleSummary());
		assertEquals(2, vehicleSummary.size());
		int index = vehicleSummary.size() - 1;
		System.out.println("index = " + index);
		VehicleSummary lastEl = vehicleSummary.remove(index);
		assertNotNull(lastEl);
		assertEquals(1, vehicleSummary.size());
	}
}
