package com.medhead.urgencyManagement.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerITests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetHospital() throws Exception {
        String latitude = "52.43719482421875";
        String longitude = "-3.8471927642822266";
        String pathology = "Allergy";
        String ambulanceId = "5";

        mockMvc.perform(MockMvcRequestBuilders
                .get("/urgency/{latitude}/{longitude}/{pathology}/{ambulance_id}", latitude, longitude, pathology, ambulanceId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(33)));
    }

}

