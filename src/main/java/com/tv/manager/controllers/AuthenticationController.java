package com.tv.manager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tv.manager.models.User;
import com.tv.manager.security.models.AuthenticatedUser;
import com.tv.manager.security.models.UserCredentials;
import com.tv.manager.services.AuthenticationService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authService;

	@PostMapping
	public ResponseEntity<AuthenticatedUser> login(@RequestBody UserCredentials credentials) {
		logger.info(String.format("User with the %s registration is trying to authenticate.", credentials.getEmail()));

		User user = this.authService.authenticate(credentials);
		String token = this.authService.tokenFor(user);
		return ResponseEntity.ok(new AuthenticatedUser(token, user));
	}
}
