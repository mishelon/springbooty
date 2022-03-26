package com.mishelon.base.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mishelon.base.dto.UserDTO;
import com.mishelon.base.entities.UserEntity;
import com.mishelon.base.exceptions.ValidationException;
import com.mishelon.base.repositories.UserRepository;
import com.mishelon.base.util.DtoValidator;

/**
 * User service implementation
 *
 * @author mishelon
 * @since 4 feb 2022
 */
@Service
public class UserServiceImpl implements UserService {

  private ModelMapper model = new ModelMapper();

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private DtoValidator validator;

  @Override
  public UserDTO findById(String id) {
    UserEntity userEntity = userRepository.findById(id).orElse(null);

    UserDTO userDTO = null;
    if (userEntity != null) {
      userDTO = model.map(userEntity, UserDTO.class);
    }

    return userDTO;
  }


  @Override
  public UserDTO update(UserDTO userDTO) throws ValidationException {
    validator.validate(userDTO);
    UserEntity userEntity = model.map(userDTO, UserEntity.class);
    userRepository.save(userEntity);
    if (userEntity != null) {
      userDTO = model.map(userEntity, UserDTO.class);
    }
    return userDTO;
  }
}
