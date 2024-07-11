package com.mishelon.base.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
  @Email
  @NotNull(message = "{user.email.notnull}")
  @Size(min = 4, max = 15, message = "{user.email.validation}")
  private String email;
  private String id;
  @NotNull(message = "{user.login.notnull}")
  @Size(min = 4, max = 15, message = "{user.login.validation}")
  private String login;
  @NotNull(message = "{user.name.notnull}")
  @Size(min = 4, max = 15, message = "{user.name.validation}")
  private String name;
  @NotNull(message = "{user.surname.notnull}")
  @Size(min = 4, max = 15, message = "{user.surname.validation}")
  private String surname;
}
