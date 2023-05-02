package com.medhead.urgencyManagement.controller;

import com.medhead.urgencyManagement.entity.Hospital;
import com.medhead.urgencyManagement.service.IUrgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableFeignClients(basePackages = {"com.medhead.urgencyManagement.repository"})
public class UrgencyController {

    @Autowired
    IUrgencyService urgencyService;

    @GetMapping("urgency/{latitude}/{longitude}/{pathology}/{ambulance_id}")
    public Hospital retrieveClosestHospital(@PathVariable("latitude") String latitude,
                                            @PathVariable("longitude") String longitude,
                                            @PathVariable("pathology") String pathology,
                                            @PathVariable("ambulance_id") String ambulanceId) {
        return urgencyService.getClosestHospitalBySpeciality(latitude, longitude, pathology, ambulanceId);
    }
}
