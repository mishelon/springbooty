package com.mishelon.base.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import com.mishelon.base.entities.UserEntity;


/**
 * User JPA repository interface
 *
 * @author mishelon
 * @since 4 feb 2022
 */
@Component
public interface UserRepository extends CrudRepository<UserEntity, String> {

}
