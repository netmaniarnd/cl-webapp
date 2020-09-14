package com.checklod.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
public class DataManageController {

	@GetMapping("/dev-datamng")
	public String reports(Model model) {

		model.addAttribute("currentPage", "datamng");
		return "dev-datamng";
	}
}
