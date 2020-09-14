package com.checklod.web.controller;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.checklod.service.FrontDTO;
import com.checklod.service.PointTemp;
import com.checklod.service.ReportService;
import com.checklod.service.TripDTO;
import com.checklod.service.TripDetailDTO;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@GetMapping("/front")
    public String front(Model model) {
  
		FrontDTO data = reportService.getFront();
        log.debug(data.toString());
        // TODO need to be modified
        model.addAttribute("packages", null);
        // TODO need to be modified
        return "reports";
	}
	
	@GetMapping("/reports")
	public String reports(Model model) {

		List<TripDTO> packages = reportService.listAllTrips();
		model.addAttribute("packages", packages);

		return "reports";
	}

	@GetMapping("/reports/{id}")
	public String reportDetails(@PathVariable("id") String tripId, Model model) {

		log.debug("tripId = {}", tripId);
		long id = Long.parseLong(tripId);
		TripDetailDTO trip = reportService.findTrip(id);
		log.debug("trip = {}", trip);

		long hours = ChronoUnit.HOURS.between(trip.getReportSummary().getPointStart(),
				trip.getReportSummary().getPointEnd());
		long minutes = ChronoUnit.MINUTES.between(trip.getReportSummary().getPointStart(),
				trip.getReportSummary().getPointEnd()) % 60;
		long seconds = ChronoUnit.SECONDS.between(trip.getReportSummary().getPointStart(),
				trip.getReportSummary().getPointEnd()) % 60;
		String duration = String.format("%d시간 %d분 %d초", hours, minutes, seconds);
		
		System.out.println(trip);
		
		model.addAttribute("trip", trip);
		model.addAttribute("duration", duration);
		return "report-details";
	}

	@GetMapping(value = "report-chart/{id}")
	public @ResponseBody String reportChart(Locale locale, Model model, @PathVariable("id") String tripId) {

		System.out.println(tripId);

		Gson gson = new Gson();

		long id = Long.parseLong(tripId);
		
		System.out.println(id);
		
		List<PointTemp> list = reportService.findTrip(id).getListTemp();

		return gson.toJson(list);
	}
}
