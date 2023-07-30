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

    static private final String url = "/urgency/{latitude}/{longitude}/{pathology}/{ambulance_id}";
    static private final String latitude = "51.585049";
    static private final String longitude = "-0.175270";
    static private final String pathology = "Allergy";
    static private final String ambulanceId = "5";

    @Test
    @WithMockUser
    public void getHospitalShouldReturnClosestHospitalForAuthenticatedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(url, latitude, longitude, pathology, ambulanceId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(214)));
    }

    @Test
    public void getHospitalShouldReturnUnauthorizedForUnauthenticatedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(url, latitude, longitude, pathology, ambulanceId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}

