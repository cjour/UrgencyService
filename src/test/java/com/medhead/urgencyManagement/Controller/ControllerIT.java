package com.medhead.urgencyManagement.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testGetHospital() throws Exception {
        String latitude = "51.585049";
        String longitude = "-0.175270";
        String pathology = "Allergy";
        String ambulanceId = "5";

        mockMvc.perform(MockMvcRequestBuilders
                .get("/urgency/{latitude}/{longitude}/{pathology}/{ambulance_id}", latitude, longitude, pathology, ambulanceId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(214)));
    }
}

