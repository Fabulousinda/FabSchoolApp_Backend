package com.fabiit.fabschoolapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//@CrossOrigin(origins = "http://localhost:4200")
@EnableWebSecurity
public class FabSchoolAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabSchoolAppApplication.class, args);
	}

}
