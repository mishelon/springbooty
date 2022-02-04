package com.mishelon.base.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.mishelon.base.entities.UserEntity;

@Component
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
