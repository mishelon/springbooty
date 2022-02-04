package com.mishelon.base.services;

import com.mishelon.base.entities.UserEntity;

public interface UserService {

	UserEntity findById(Long id);

}