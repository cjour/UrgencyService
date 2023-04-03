package com.medhead.urgencyManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class UrgencyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrgencyManagementApplication.class, args);
	}

}
