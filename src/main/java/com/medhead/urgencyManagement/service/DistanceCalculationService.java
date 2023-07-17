package com.medhead.urgencyManagement.service;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class DistanceCalculationService implements IDistanceCalculationService {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DistanceCalculationService.class));

    @Override
    public Integer getDistanceBetweenHospitalAndEmergency(String origins, String hospitalLatitude, String hospitalLongitude) {

        String baseApiUrl = "http://localhost:8989/route";
        String getDistance = baseApiUrl + "?point=" +
                origins + "&point=" +
                hospitalLatitude + "," +
                hospitalLongitude + "&profile=car";

        RestTemplate restTemplate = new RestTemplate();

        JSONObject response = restTemplate.getForObject(getDistance, JSONObject.class );

        Double distanceDouble = JsonPath.read(response, "$['paths'][0]['distance']");

        Integer distance = distanceDouble.intValue();

        LOGGER.info("Distance between hospital and emergency is " + distance);

        return distance;
    }
}

