package com.mishelon.base.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
@Table(name = UserEntity.TABLE_NAME)
public class UserEntity extends AuditableEntity {

  public static final String TABLE_NAME = "USERS";

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(nullable = false, updatable = false, insertable = false, unique = true)
  private String id;

  @NotBlank
  @Column(nullable = false, unique = true)
  @Size(min = 3, max = 16, message = "Entre 3 y 16")
  private String login;

  @NotBlank
  @Size(min = 1, max = 50, message = "Entre 1 y 50")
  private String name;

  @NotBlank
  private String surname;

}
