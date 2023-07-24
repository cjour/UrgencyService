package com.medhead.urgencyManagement.Service;

import com.medhead.urgencyManagement.entity.Hospital;
import com.medhead.urgencyManagement.service.IUrgencyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UrgencyServiceIT {
    @Autowired
    private IUrgencyService urgencyService;

    @Test
    public void shouldReturnClosestHospital() throws Exception {
        //GIVEN
        String latitude = "51.585049";
        String longitude = "-0.175270";
        String pathology = "Allergy";
        String ambulanceId = "5";

        //WHEN
        Hospital hospital = urgencyService.getClosestHospitalBySpeciality(latitude, longitude, pathology, ambulanceId);

        //THEN
        // Closest hospital id is 214.
        Assertions.assertEquals(hospital.getId(), 214);
    }
}
