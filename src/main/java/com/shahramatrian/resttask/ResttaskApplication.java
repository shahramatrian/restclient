package com.shahramatrian.resttask;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ResttaskApplication {
	private final String baseURL = "https://dashboard.healthit.gov/api/open-api.php";
	private static final Logger log = LoggerFactory.getLogger(ResttaskApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ResttaskApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() throws RestClientException{
		return args -> {
			String stateHealthArray = getHealthInfoByYear("2014", "AHA_2008-2015.csv", "region", "desc");

			List<StateHealth> healthRecords = getHealthRecords(stateHealthArray);
			for (StateHealth stateHealth : healthRecords) {
				if (!stateHealth.getRegion().equals("National")) { // remove "National" from states
					System.out.println(stateHealth);
				}
			}
		};
	}

	private String getHealthInfoByYear(String year, String source, String sortBy, String sortDir) {
		String endPoint = this.baseURL + "?source=" + source + "&sort=" + sortBy + "&sort_dir=" + sortDir + "&period=" + year;

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(endPoint, String.class);
	}

	/**
	 * Convert a JSON array string to a list of StateHealth using custon JSON deserializer
	 * @param stateHealthArray
	 * @return list of StateHealth records
	 * @throws JsonProcessingException
	 */
	private List<StateHealth> getHealthRecords(String stateHealthArray) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module =
		new SimpleModule("StateHealthDeserializer", new Version(1, 0, 0, null, null, null));
		module.addDeserializer(StateHealth.class, new StateHealthDeserializer());
		objectMapper.registerModule(module);

		CollectionType javaType = objectMapper.getTypeFactory()
			.constructCollectionType(List.class, StateHealth.class);
    	return objectMapper.readValue(stateHealthArray, javaType);
	}
}

