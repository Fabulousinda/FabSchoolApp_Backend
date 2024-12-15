package com.fabiit.fabschoolapp.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

	@GetMapping("/")
	public String name() {
		return "Welcome";
	}
}
