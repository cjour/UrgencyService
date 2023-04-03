package com.medhead.urgencyManagement.service;

import com.medhead.urgencyManagement.entity.DistanceMatrixResponse;
import com.medhead.urgencyManagement.entity.DistanceMatrixRow;
import com.medhead.urgencyManagement.entity.Hospital;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IUrgencyService {
    public Hospital getClosestHospitalBySpeciality(String latitude, String longitude, String pathology, String ambulanceId);
}
