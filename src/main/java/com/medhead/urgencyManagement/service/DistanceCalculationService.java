package com.medhead.urgencyManagement.service;

import com.medhead.urgencyManagement.entity.DistanceMatrixResponse;
import com.medhead.urgencyManagement.repository.DistanceCalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistanceCalculationService implements IDistanceCalculationService {

    DistanceCalculationRepository distanceCalculationRepository;

    @Override
    public DistanceMatrixResponse getDistance(String destinations, String origins, String key) {
        return distanceCalculationRepository.getDistance(destinations, origins, key);
    }
}
