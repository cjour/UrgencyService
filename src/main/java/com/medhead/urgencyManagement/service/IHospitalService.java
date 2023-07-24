package com.medhead.urgencyManagement.service;

import com.medhead.urgencyManagement.entity.Hospital;
import java.util.List;

public interface IHospitalService {
    List<Hospital> findBySpeciality(String pathology);
}
