package com.tv.manager.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.manager.exceptions.FailedAuthenticationException;
import com.tv.manager.models.User;
import com.tv.manager.security.models.UserCredentials;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthenticationService {
	private final String ISSUER = "random";
	private final String secretKey = "random";

	@Autowired
	private UserService userService;

	public User authenticate(UserCredentials credentials) {
		User user = userService.getByEmail(credentials.getEmail());

		if (credentials.getPassword().equals(user.getPassword())) {
			return user;
		}
		throw new FailedAuthenticationException();
	}

	public String tokenFor(User user) {
		Date expiration = Date.from(LocalDateTime.now().plusHours(24 * 7).toInstant(ZoneOffset.UTC));
		return Jwts.builder().setSubject(user.getEmail()).setExpiration(expiration).setIssuer(ISSUER)
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public User getUserFromToken(String token) {
		String tkn = token.substring(7);
		Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(tkn);
		return userService.getByEmail(claims.getBody().getSubject());
	}
}