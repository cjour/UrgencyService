package com.medhead.urgencyManagement.Service;

import com.medhead.urgencyManagement.entity.*;
import com.medhead.urgencyManagement.repository.HospitalRepository;
import com.medhead.urgencyManagement.service.DistanceCalculationService;
import com.medhead.urgencyManagement.service.HospitalService;
import com.medhead.urgencyManagement.service.IUrgencyService;
import com.medhead.urgencyManagement.service.UrgencyService;
import com.medhead.urgencyManagement.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UrgencyServiceUTests {

    @Autowired
    private IUrgencyService urgencyService;

    @MockBean
    private HospitalService hospitalsService;

    @MockBean
    private DistanceCalculationService distanceCalculationService;

    private final StringUtils stringUtils = new StringUtils();
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(UrgencyService.class));

    @Test
    public void shouldReturnClosestHospitalBySpeciality() {
        //GIVEN
        // Parameter for urgency service.
        String latitude = "-37.80454585386352";
        String longitude = "175.28305848434036";
        String speciality = "Allergy";
        String ambulanceId = "1";

        // Return of HospitalManagement API.
        given(hospitalsService.findBySpeciality(speciality)).willReturn(
                new ArrayList<>(
                    Arrays.asList(
                            new Hospital(1,"Auckland hospital","","","","Auckland",
                                    "Auckland - NZ","","-36.85937761392017", "174.77031683092022",
                                    new ArrayList<>(Collections.singletonList(new Pathology(1, "Allergy")))
                            ),
                            new Hospital(
                                    2,"Waikato Hospital","","","","Auckland",
                                    "Auckland - NZ","","-37.80454585386352", "175.28305848434036",
                                    new ArrayList<>(Collections.singletonList(new Pathology(2, "Allergy")))
                            )
                    )
                )
        );

        // Return for distance calculation service.
        String origins = stringUtils.buildCoordinates("-36.848374", "174.763556", StringUtils.spaceSeparator);
        String destinations = stringUtils.buildCoordinates("-36.85937761392017", "174.77031683092022", StringUtils.spaceSeparator);
        given(distanceCalculationService.getDistance(any(), any(), any())).willReturn(
                new DistanceMatrixResponse(
                    null,
                    null,
                    Arrays.asList(
                            new DistanceMatrixRow(
                                    new ArrayList<>(
                                            Collections.singletonList(
                                                    new DistanceMatrixElement(
                                                            DistanceMatrixElementStatus.OK,
                                                            new TextValueObject("", 10001),
                                                            new TextValueObject("", 2621)
                                                    )
                                            )
                                    )
                            ),
                            new DistanceMatrixRow(
                                    new ArrayList<>(
                                            Collections.singletonList(
                                                    new DistanceMatrixElement(
                                                            DistanceMatrixElementStatus.OK,
                                                            new TextValueObject("", 806),
                                                            new TextValueObject("", 2301)
                                                    )
                                            )
                                    )
                            )
                    ),
                    DistanceMatrixStatus.OK,
                    null
                )
        );

        //WHEN
        Hospital hospital = urgencyService.getClosestHospitalBySpeciality(latitude, longitude, speciality, ambulanceId);

        //THEN
        Assertions.assertEquals(hospital.getId(), 2);
    }
}
