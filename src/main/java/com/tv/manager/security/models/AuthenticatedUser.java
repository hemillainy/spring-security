package com.tv.manager.security.models;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tv.manager.models.User;

@JsonInclude(Include.NON_NULL)
public class AuthenticatedUser implements Authentication {

	private static final long serialVersionUID = 1L;
	private String token;
	private User user;

	public AuthenticatedUser(User user) {
		this.user = user;
	}

	public AuthenticatedUser(String token) {
		this.token = token;
	}

	public AuthenticatedUser(String token, User user) {
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	@JsonIgnore
	public String getName() {
		return null;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<Role> roles = new ArrayList<>();
//		for (Profile profile : this.user.getProfiles()) {
//			for (Role role : profile.getRoles()) {
//				roles.add(role);
//			}
//		}
		return null;
	}

	@Override
	@JsonIgnore
	public Object getCredentials() {
		return this.token;
	}

	@Override
	@JsonIgnore
	public Object getDetails() {
		return null;
	}

	@Override
	@JsonIgnore
	public Object getPrincipal() {
		return null;
	}

	@Override
	@JsonIgnore
	public boolean isAuthenticated() {
		return this.user != null;
	}

	@Override
	@JsonIgnore
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
	}
}
