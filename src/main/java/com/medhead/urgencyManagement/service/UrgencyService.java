package com.medhead.urgencyManagement.service;

import com.medhead.urgencyManagement.entity.*;
import com.medhead.urgencyManagement.repository.DistanceCalculationRepository;
import com.medhead.urgencyManagement.repository.HospitalRepository;
import com.medhead.urgencyManagement.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Hospital> hospitals = hospitalService.findBySpeciality(pathology);
        String origins = stringUtils.buildCoordinates(latitude, longitude, StringUtils.spaceSeparator);
        Map<Hospital, Integer> hospitalsMap = this.getHospitalsByDurationFromOrigins(origins, hospitals);
        return this.getClosestHospital(hospitalsMap);
    }

    public Map<Hospital, Integer> getHospitalsByDurationFromOrigins(String origins, List<Hospital> hospitals) {
        Map<Hospital, Integer> hospitalsMap = new HashMap<>();

        for (Hospital hospital : hospitals) {
            String destinations = stringUtils.buildCoordinates(hospital.getLatitude(), hospital.getLongitude(), StringUtils.spaceSeparator);
            DistanceMatrixResponse distanceMatrixResponse = distanceCalculationService.getDistance(origins, destinations, secretKey);
            if(distanceMatrixResponse.status.equals(DistanceMatrixStatus.OK)) {
                for (DistanceMatrixRow row :distanceMatrixResponse.rows) {
                    for (DistanceMatrixElement element: row.elements) {
                        if(element.duration != null) {
                            hospitalsMap.put(hospital, element.duration.value);
                        }
                    }
                }
            }
        }
        return hospitalsMap;
    }

    private Hospital getClosestHospital(Map<Hospital, Integer> hospitalsMap) {
        int duration = Integer.MAX_VALUE;
        Hospital closestHospital = null;
        for (Map.Entry<Hospital, Integer> entry : hospitalsMap.entrySet()) {
            if (entry.getValue() < duration) {
                duration = entry.getValue();
                closestHospital = entry.getKey();
            }
        }
        return closestHospital;
    }
}
