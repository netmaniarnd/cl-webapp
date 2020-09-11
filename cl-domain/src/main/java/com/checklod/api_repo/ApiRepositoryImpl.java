package com.checklod.api_repo;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiRepositoryImpl implements ApiRepository {
	
    // Both RestTemplate and URI instances can be cached
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final URI uri = URI.create("https://dev.checklod.com/tnt-ex-mgr-pilot/service/api.php/dashboards/goods");
    //private static final URI uri2 = URI.create("https://dev.checklod.com/tnt-ex-mgr-pilot/service/api.php/dashboards/brackaway");
    private static final URI uri3 = URI.create("https://dev.checklod.com/tnt-ex-mgr-pilot/service/api.php/dashboards/brackawaytrip");

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
	    	VehicleSummary row = new VehicleSummary();
	    	row.setName(item.getVehicleNo());
	    	row.setTotal(item.getTotal());
	    	row.setGoing(item.getState2());
	    	row.setArrived(item.getState3());
	    	row.setSigned(item.getState4());
	    	row.setReported(item.getState5());
	    	summary.add(row);
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

}
