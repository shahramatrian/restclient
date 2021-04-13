package com.shahramatrian.resttask.web;

import java.util.List;

import com.shahramatrian.resttask.business.domain.StateHealth;
import com.shahramatrian.resttask.business.service.HealthStateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statehealth")
public class StateHealthWebServiceController {
    private final HealthStateService healthStateService;

    @Autowired
    public StateHealthWebServiceController(HealthStateService healthStateService) {
        this.healthStateService = healthStateService;
    }

    @GetMapping
    public List<StateHealth> getHealthRecords(){
        return healthStateService.getBasicEHR("2014", "AHA_2008-2015.csv", "region", "desc");
    }
}
