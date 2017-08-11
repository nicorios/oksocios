package com.oksocios;

import com.oksocios.service.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
public class OksociosApiApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(OksociosApiApplication.class, args);

		GoogleMapsService googleMapsService = new GoogleMapsService(new RestTemplate());

		googleMapsService.getCandidateById("San Jeronimo", 2486);
	}
}
