package com.medhead.urgencyManagement.service;

public interface IDistanceCalculationService {
    Integer getDistanceBetweenHospitalAndEmergency(String origins, String hospitalLatitude, String hospitalLongitude);
}
