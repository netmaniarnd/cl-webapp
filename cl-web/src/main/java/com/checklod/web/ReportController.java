package com.checklod.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.checklod.service.ReportService;
import com.checklod.service.TripDTO;
import com.checklod.service.TripDetailDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@GetMapping("/reports")
    public String reports(Model model) {
		
        List<TripDTO> packages = reportService.listAllTrips();
		model.addAttribute("packages", packages);
		return "reports";
	}

	@GetMapping("/reports/{id}")
    public String reportDetails(@PathVariable("id") String tripId, Model model) {
		
		log.debug("tripId = {}", tripId);
		long id = Long.parseLong(tripId);//1538;
		TripDetailDTO trip = reportService.findTrip(id );
		log.debug("trip = {}", trip);
		model.addAttribute("trip", trip);
		return "report-details";
	}
}
