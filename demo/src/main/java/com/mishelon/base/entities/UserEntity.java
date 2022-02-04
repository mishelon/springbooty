package com.mishelon.base.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class UserEntity {
	
	@Id
	private Long id;

	@NotBlank
	private String name;
	
}
