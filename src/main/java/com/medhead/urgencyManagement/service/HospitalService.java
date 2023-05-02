package com.medhead.urgencyManagement.service;

import com.medhead.urgencyManagement.entity.Hospital;
import com.medhead.urgencyManagement.repository.HospitalRepository;

import java.util.List;

public class HospitalService implements IHospitalService{

    HospitalRepository hospitalRepository;

    @Override
    public List<Hospital> findBySpeciality(String pathology) {
        return hospitalRepository.findBySpeciality(pathology);
    }
}
