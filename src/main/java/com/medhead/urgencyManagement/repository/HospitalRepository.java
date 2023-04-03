package com.medhead.urgencyManagement.repository;

import com.medhead.urgencyManagement.entity.Hospital;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// Mettre l'URL de la gateway directement (ou plutôt le nom de la gateway de Eureka).
@FeignClient(name = "microservice-hospital", url = "http://localhost:8080")
public interface HospitalRepository {

    @GetMapping(value = "/hospital/{speciality}")
    List<Hospital> findBySpeciality(@PathVariable("speciality") String pathology);
}
