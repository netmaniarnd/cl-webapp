package com.checklod.api_repo;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiRepositoryImpl implements ApiRepository {
	
    private static final String URI_TEMPLATE_VEHICLE_TRIP = "https://dev.checklod.com/tnt-ex-mgr-pilot/service/dashboard-vehicle.php?id=%s&duration=0";
	// Both RestTemplate and URI instances can be cached
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final URI uri = URI.create("https://dev.checklod.com/tnt-ex-mgr-pilot/service/api.php/dashboards/goods");
    //private static final URI uri2 = URI.create("https://dev.checklod.com/tnt-ex-mgr-pilot/service/api.php/dashboards/brackaway");
    private static final URI uri3 = URI.create("https://dev.checklod.com/tnt-ex-mgr-pilot/service/api.php/dashboards/brackawaytrip");
    //private static final URI uri4 = URI.create(URI_TEMPLATE_VEHICLE_TRIP);

	@Override
	public List<VehicleSummary> getVehicleSummary() {
		//ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(uri, Object[].class);
	    //System.out.println(responseEntity.getBody());
	    //System.out.println(responseEntity);
		//
		List<VehicleSummary> summary = new ArrayList<VehicleSummary>();
	    //
		VehicleSummaryAdapter[] forNow = restTemplate.getForObject(uri, VehicleSummaryAdapter[].class);
	    List<VehicleSummaryAdapter> summaryTemp = Arrays.asList(forNow);
	    summaryTemp.forEach(item -> {
	    	System.out.println(item.toString());
			VehicleSummary row = item.convert();
	    	summary.add(row );
	    });
    	System.out.println("array size = " + summary.size());
	    //
		//String result1 = restTemplate.getForObject(uri, String.class);
	    //System.out.println(result1);
		return summary ;
	}

	@Override
	public List<Long> getRunawayList() {
		RunawayListVO forNow = restTemplate.getForObject(uri3, RunawayListVO.class);
	    //List<Object> summary = Arrays.asList(forNow);
	    //summary.forEach(item -> {
	    	System.out.println(forNow.toString());
	    //});
    	//System.out.println("array size = " + summary.size());
		return forNow.getExtra();
	}

	@Override
	public List<VehicleTripVO> getVehicleTrips(String vehicleNo) {
		String enc = Base64.getEncoder().encodeToString(vehicleNo.getBytes());
		// TODO Auto-generated method stub
		URI uri4 = URI.create(String.format(URI_TEMPLATE_VEHICLE_TRIP, enc));
		List<VehicleTripVO> summary = new ArrayList<VehicleTripVO>();
		VehicleTripAdapter[] forNow = restTemplate.getForObject(uri4, VehicleTripAdapter[].class);
	    List<VehicleTripAdapter> summaryTemp = Arrays.asList(forNow);
	    summaryTemp.forEach(item -> {
	    	System.out.println(item.convert());
	    	summary.add(item.convert());
	    });
		return summary;
	}

}
