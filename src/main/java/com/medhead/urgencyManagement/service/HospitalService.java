package com.medhead.urgencyManagement.service;

import com.medhead.urgencyManagement.entity.Hospital;
import com.medhead.urgencyManagement.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService implements IHospitalService{
    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    public List<Hospital> findBySpeciality(String pathology) {
        return hospitalRepository.findBySpeciality(pathology);
    }
}
