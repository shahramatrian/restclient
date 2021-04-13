package com.shahramatrian.resttask.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.shahramatrian.resttask.business.domain.StateHealth;
import com.shahramatrian.resttask.business.service.HealthStateService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(StateHealthWebController.class)
public class RoomReservationWebControllerTest {
    @MockBean
    private HealthStateService healthStateService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHealthRecords() throws Exception{
        List<StateHealth> healthRecords = new ArrayList<>();
        StateHealth stateHealth = new StateHealth();
        stateHealth.setRegion("Wyoming");
        stateHealth.setRegionCode("WY");
        stateHealth.setPeriod("2014");
        stateHealth.setPctHospitalsBasicEhrNotes("0.68");
        healthRecords.add(stateHealth);
        given(healthStateService.getBasicEHR("2014", "AHA_2008-2015.csv", "region", "desc")).willReturn(healthRecords);

        this.mockMvc.perform(get("/statehealth"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Wyoming")));

    }
}
