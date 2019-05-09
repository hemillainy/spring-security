package com.tv.manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.tv.manager.exceptions.FailedAuthenticationException;
import com.tv.manager.models.User;
import com.tv.manager.security.models.AuthenticatedUser;
import com.tv.manager.services.AuthenticationService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public Authentication authenticate(Authentication authentication) {
		try {
			User user = authenticationService.getUserFromToken((String) authentication.getCredentials());
			return new AuthenticatedUser((String) authentication.getCredentials(), user);
		} catch (Exception e) {
			throw new FailedAuthenticationException("Authentication failed: ".concat(e.getMessage()));
		}
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (AuthenticatedUser.class.isAssignableFrom(authentication));
	}

}
