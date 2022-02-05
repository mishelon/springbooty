package com.mishelon.base.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.Data;

/**
 * @author mishelon
 * @since 5 feb 2022
 */
@Data
@MappedSuperclass
public abstract class AuditableEntity {

  @CreatedBy
  private String createdBy;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private Date createdDate = new Date();

  @LastModifiedBy
  private String modifiedBy;

  @LastModifiedDate
  private Date modifiedDate;

}
