package com.mishelon.base.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mishelon.base.entities.UserEntity;
import com.mishelon.base.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserEntity findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
}
