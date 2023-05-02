package com.medhead.urgencyManagement.repository;

import com.medhead.urgencyManagement.entity.DistanceMatrixResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "distance-calculation-client", url = "https://maps.googleapis.com/maps/api/distancematrix/json")
@Repository
public interface DistanceCalculationRepository {
    @GetMapping()
    DistanceMatrixResponse getDistance(
            @RequestParam(value = "origins") String origins,
            @RequestParam(value = "destinations") String destinations,
            @RequestParam(value = "key") String key
    );
}
