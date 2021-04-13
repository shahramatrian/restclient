package com.shahramatrian.resttask.web;

import java.util.List;

import com.shahramatrian.resttask.business.domain.StateHealth;
import com.shahramatrian.resttask.business.service.HealthStateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statehealth")
public class StateHealthWebController {
    private final HealthStateService healthStateService;

    @Autowired
    public StateHealthWebController(HealthStateService healthStateService) {
        this.healthStateService = healthStateService;
    }

    @GetMapping
    public String getHealthRecords(Model model){
        List<StateHealth> healthRecords = healthStateService.getBasicEHR("2014", "AHA_2008-2015.csv", "region", "desc");
        model.addAttribute("healthStateList", healthRecords);
        return "hospitals";
    }
}
