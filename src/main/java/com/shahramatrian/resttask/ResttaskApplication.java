package com.shahramatrian.resttask;

import java.util.List;

import com.shahramatrian.resttask.business.domain.StateHealth;
import com.shahramatrian.resttask.business.service.HealthStateService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;

@SpringBootApplication
public class ResttaskApplication {
	private static final Logger log = LoggerFactory.getLogger(ResttaskApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ResttaskApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(HealthStateService healthStateService) throws RestClientException{
		return args -> {
			List<StateHealth> healthRecords = healthStateService.getBasicEHR("2014", "AHA_2008-2015.csv", "region", "desc");
			log.info(healthRecords.toString());
		};
	}
}

