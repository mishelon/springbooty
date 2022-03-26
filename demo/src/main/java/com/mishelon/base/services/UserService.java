package com.mishelon.base.services;

import com.mishelon.base.dto.UserDTO;
import com.mishelon.base.exceptions.ValidationException;

/**
 * User service interface definition
 *
 * @author mishelon
 * @since 4 feb 2022
 */
public interface UserService {

  /**
   * @param id User id
   * @return User entity if exists or null otherwise
   */
  UserDTO findById(String id);

  UserDTO update(UserDTO user) throws ValidationException;

}
