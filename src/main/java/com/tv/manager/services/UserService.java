package com.tv.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.manager.exceptions.UserNotFoundException;
import com.tv.manager.models.User;
import com.tv.manager.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User getByEmail(String email) {
		return this.userRepository.findByEmail(email)
				.map(user -> user)
				.orElseThrow(() -> new UserNotFoundException());
	}
}
