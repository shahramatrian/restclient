package com.shahramatrian.resttask.business.service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.shahramatrian.resttask.business.domain.StateHealth;
import com.shahramatrian.resttask.business.domain.StateHealthDeserializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HealthStateService {
	private final String baseURL = "https://dashboard.healthit.gov/api/open-api.php";

    @Autowired
    public HealthStateService() {
        // to be added
    }

    public List<StateHealth> getBasicEHR(String year, String source, String sortBy, String sortDir){
        String stateHealthArray = getHealthInfoByYear(year, source, sortBy, sortDir);

        List<StateHealth> healthRecords = new ArrayList<>();
        try {
            healthRecords = getHealthRecords(stateHealthArray);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		List<StateHealth> filteredHealthRecords = new ArrayList<>(); // remove National from states
		for (StateHealth stateHealth : healthRecords) {
			if (!stateHealth.getRegion().equals("National")) {
				filteredHealthRecords.add(stateHealth);
			}
		}
        return filteredHealthRecords;

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
