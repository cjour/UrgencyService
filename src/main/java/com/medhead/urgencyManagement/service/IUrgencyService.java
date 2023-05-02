package com.medhead.urgencyManagement.service;

import com.medhead.urgencyManagement.entity.DistanceMatrixResponse;
import com.medhead.urgencyManagement.entity.DistanceMatrixRow;
import com.medhead.urgencyManagement.entity.Hospital;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface IUrgencyService {
    public Hospital getClosestHospitalBySpeciality(String latitude, String longitude, String pathology, String ambulanceId);
    public Map<Hospital, Integer> getHospitalsByDurationFromOrigins(String origins, List<Hospital> hospitals);
}
