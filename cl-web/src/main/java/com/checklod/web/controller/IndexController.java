package com.checklod.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("")
public class IndexController {

	@GetMapping("/index")
	public String index() {
		log.debug("**************************************index");
		return "/index";
	}
}
