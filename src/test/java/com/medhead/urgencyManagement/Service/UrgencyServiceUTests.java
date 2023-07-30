package com.medhead.urgencyManagement.Service;

import com.medhead.urgencyManagement.configuration.JsonWebTokenFilter;
import com.medhead.urgencyManagement.entity.Hospital;
import com.medhead.urgencyManagement.entity.Pathology;
import com.medhead.urgencyManagement.service.DistanceCalculationService;
import com.medhead.urgencyManagement.service.HospitalService;
import com.medhead.urgencyManagement.service.IUrgencyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UrgencyServiceUTests {

    @Autowired
    private IUrgencyService urgencyService;

    @Autowired
    JsonWebTokenFilter jsonWebTokenFilter;

    @MockBean
    private HospitalService hospitalsService;

    @MockBean
    private DistanceCalculationService distanceCalculationService;

    @Test
    public void shouldReturnClosestHospitalBySpeciality() {
        //GIVEN
        // Parameter for urgency service.
        String latitude = "51.585049";
        String longitude = "-0.175270";
        String speciality = "Allergy";
        String ambulanceId = "1";

        // Return of HospitalManagement API.
        given(hospitalsService.findBySpeciality(speciality)).willReturn(
                new ArrayList<>(
                    Arrays.asList(
                            new Hospital(1,"Auckland hospital","","","","Auckland",
                                    "Auckland - NZ","","51.585079", "-0.175280",
                                    new ArrayList<>(Collections.singletonList(new Pathology(1, "Allergy")))
                            ),
                            new Hospital(
                                    2,"Waikato Hospital","","","","Auckland",
                                    "Auckland - NZ","","52.585079", "-0.175280",
                                    new ArrayList<>(Collections.singletonList(new Pathology(2, "Allergy")))
                            )
                    )
                )
        );

        when(distanceCalculationService.getDistanceBetweenHospitalAndEmergency(any(), any(), any())).thenReturn(1000,500);

        //WHEN
        Hospital hospital = urgencyService.getClosestHospitalBySpeciality(latitude, longitude, speciality, ambulanceId);

        //THEN
        Assertions.assertEquals(2, hospital.getId());
    }
}
