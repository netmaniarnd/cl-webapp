package com.checklod.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.checklod.web","com.checklod.service","com.checklod.domain", "com.checklod.api_repo"})
@EnableJpaRepositories("com.checklod.domain")
@EntityScan("com.checklod.domain")
public class ChecklodDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChecklodDashboardApplication.class, args);
	}

}
