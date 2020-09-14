package com.checklod.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checklod.service.FrontDTO;
import com.checklod.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("")
public class FrontController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/front")
    public String front(Model model) {
  
		FrontDTO data = reportService.getFront();
        log.debug(data.toString());
        model.addAttribute("packages", data);
        return "/front";
	}
}
