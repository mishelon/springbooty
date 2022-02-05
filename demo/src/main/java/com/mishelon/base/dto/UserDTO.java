package com.mishelon.base.dto;

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
  private String id;
  private String login;
  private String name;
  private String surname;
}
