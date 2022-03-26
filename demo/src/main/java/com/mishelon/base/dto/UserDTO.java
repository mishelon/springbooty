package com.mishelon.base.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mishelon
 * @since 4 feb 2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  @Size(min = 4, max = 15, message = "{user.email.validation}")
  @Email
  private String email;
  private String id;
  @Size(min = 4, max = 15, message = "{user.login.validation}")
  private String login;
  @Size(min = 4, max = 15, message = "{user.name.validation}")
  private String name;
  @Size(min = 4, max = 15, message = "{user.surname.validation}")
  private String surname;
}
