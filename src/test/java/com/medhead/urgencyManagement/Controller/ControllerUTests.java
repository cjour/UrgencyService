package com.medhead.urgencyManagement.Controller;

import com.medhead.urgencyManagement.configuration.JsonWebTokenFilter;
import com.medhead.urgencyManagement.controller.UrgencyController;
import com.medhead.urgencyManagement.repository.AuthorizationRepository;
import com.medhead.urgencyManagement.service.UrgencyService;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.logging.Logger;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UrgencyController.class)
public class ControllerUTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrgencyService urgencyService;

    @MockBean
    AuthorizationRepository authorizationRepository;

    @Test
    @WithMockUser
    public void testGetHospital() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/urgency/{latitude}/{longitude}/{pathology}/{ambulance_id}", "52.43719482421875", "-3.8471927642822266", "Allergy", "5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetHospitalUnauthenticatedUser() throws Exception {
        //test commit
        mockMvc.perform(MockMvcRequestBuilders
                .get("/urgency/{latitude}/{longitude}/{pathology}/{ambulance_id}", "52.43719482421875", "-3.8471927642822266", "Allergy", "5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
