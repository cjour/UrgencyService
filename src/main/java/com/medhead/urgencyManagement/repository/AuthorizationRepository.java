package com.medhead.urgencyManagement.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthorizationRepository {
    private final Logger logger = LoggerFactory.getLogger(AuthorizationRepository.class);

    public Boolean isTokenValid(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(token);
        ResponseEntity<Boolean> response = restTemplate.exchange(
                "http://localhost:8084/isTokenValid",
                HttpMethod.POST,
                request,
                Boolean.class
        );

        logger.info("Authorization call " + response.getStatusCode().toString());
        logger.info(response.getBody().toString());

        return response.getBody();
    }
}
