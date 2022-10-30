package com.example.businessintelligencemicroservice;

import com.example.businessintelligencemicroservice.Model.Sale;
import com.example.businessintelligencemicroservice.Repository.SalesRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.function.Consumer;

@SpringBootApplication
public class BusinessIntelligenceMicroserviceApplication {


	public static void main(String[] args) {
		SpringApplication.run(BusinessIntelligenceMicroserviceApplication.class, args);

	}



}
