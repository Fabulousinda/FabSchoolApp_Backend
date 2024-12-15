package com.fabiit.fabschoolapp.auth.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import com.fabiit.fabschoolapp.auth.dtos.LoginUserDto;
import com.fabiit.fabschoolapp.auth.dtos.RegisterUserDto;
import com.fabiit.fabschoolapp.auth.entity.User;
import com.fabiit.fabschoolapp.auth.responses.LoginResponse;
import com.fabiit.fabschoolapp.auth.service.AuthenticationService;
import com.fabiit.fabschoolapp.auth.service.JwtService;
import com.fabiit.fabschoolapp.auth.service.UserService;


@RequestMapping("/auth")
@RestController
public class AuthenticationController {
	private final JwtService jwtService;

	private final AuthenticationService authenticationService;
	@Autowired
	private UserService userService;

	public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
		this.jwtService = jwtService;
		this.authenticationService = authenticationService;
	}

	@PostMapping("/signup")
	public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
		User registeredUser = authenticationService.signup(registerUserDto);

		return ResponseEntity.ok(registeredUser);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
		User authenticatedUser = authenticationService.authenticate(loginUserDto);

		String jwtToken = jwtService.generateToken(authenticatedUser);

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setToken(jwtToken);
		loginResponse.setExpiresIn(jwtService.getExpirationTime());

		return ResponseEntity.ok(loginResponse);
	}
	
	@GetMapping("/whoAmI")
	public ResponseEntity<User> currentUserName(Authentication authentication) {
		User user = null;
		if (authentication != null) {
			Optional<User> userByEmail = userService.getUserByEmail(authentication.getName());
			if(userByEmail.isPresent()) {
				user = userByEmail.get();
			}
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);

	}
}