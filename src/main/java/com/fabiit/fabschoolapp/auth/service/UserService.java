package com.fabiit.fabschoolapp.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fabiit.fabschoolapp.auth.entity.User;
import com.fabiit.fabschoolapp.auth.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

	public Optional<User> getUserByEmail(String name) {
		// TODO Auto-generated method stub
		
		return userRepository.findByEmail(name);
	}
    
}