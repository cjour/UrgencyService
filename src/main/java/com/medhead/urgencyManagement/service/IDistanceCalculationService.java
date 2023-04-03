package com.medhead.urgencyManagement.service;

import com.medhead.urgencyManagement.entity.DistanceMatrixResponse;

public interface IDistanceCalculationService {
    public DistanceMatrixResponse getDistance(String destinations, String origins, String key);
}
