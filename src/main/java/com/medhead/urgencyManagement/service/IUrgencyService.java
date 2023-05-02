package com.medhead.urgencyManagement.service;

import com.medhead.urgencyManagement.entity.Hospital;

import java.util.List;
import java.util.Map;


public interface IUrgencyService {
    Hospital getClosestHospitalBySpeciality(String latitude, String longitude, String pathology, String ambulanceId);
    Map<Hospital, Integer> getHospitalsByDurationFromOrigins(String origins, List<Hospital> hospitals);
}
