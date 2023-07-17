package com.medhead.urgencyManagement.service;

import com.medhead.urgencyManagement.entity.Hospital;
import com.medhead.urgencyManagement.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class UrgencyService implements IUrgencyService {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    DistanceCalculationService distanceCalculationService;

    private final StringUtils stringUtils = new StringUtils();

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(UrgencyService.class));

    @Override
    public Hospital getClosestHospitalBySpeciality(String latitude, String longitude, String pathology, String ambulanceId) {
        StopWatch stopWatch = new StopWatch("getClosestHospitalBySpeciality");
        stopWatch.start();
        List<Hospital> hospitals = hospitalService.findBySpeciality(pathology);
        String origins = stringUtils.buildCoordinates(latitude, longitude, StringUtils.spaceSeparator);
        Map<Hospital, Integer> hospitalsMap = this.getHospitalsByDurationFromOrigins(origins, hospitals);
        stopWatch.stop();
        LOGGER.info("time spend getClosestHospitalBySpeciality: " + stopWatch.getTotalTimeSeconds());
        return this.getClosestHospital(hospitalsMap);
    }

    public Map<Hospital, Integer> getHospitalsByDurationFromOrigins(String origins, List<Hospital> hospitals) {
        StopWatch stopWatch = new StopWatch("getHospitalsByDurationFromOrigins");
        stopWatch.start();
        Map<Hospital, Integer> hospitalsMap = new HashMap<>();

        for (Hospital hospital : hospitals) {
            Integer distance = distanceCalculationService.getDistanceBetweenHospitalAndEmergency(
                    origins,
                    hospital.getLatitude(),
                    hospital.getLongitude());
            hospitalsMap.put(hospital, distance);
        }
        stopWatch.stop();
        LOGGER.info("time spend getHospitalsByDurationFromOrigins: " + stopWatch.getTotalTimeSeconds());
        return hospitalsMap;
    }

    private Hospital getClosestHospital(Map<Hospital, Integer> hospitalsMap) {
        StopWatch stopWatch = new StopWatch("getClosestHospital");
        stopWatch.start();

        int duration = Integer.MAX_VALUE;
        Hospital closestHospital = null;
        for (Map.Entry<Hospital, Integer> entry : hospitalsMap.entrySet()) {
            if (entry.getValue() < duration) {
                duration = entry.getValue();
                closestHospital = entry.getKey();
            }
        }
        stopWatch.stop();
        LOGGER.info("time spend getClosestHospital: " + stopWatch.getTotalTimeSeconds());
        return closestHospital;
    }
}
