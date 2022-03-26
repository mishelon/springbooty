package com.mishelon.base.util;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mishelon.base.exceptions.ValidationException;

/**
 * @author mishelon
 * @since 26 mar 2022
 */
@Component
public class DtoValidator {

  @Autowired
  private Validator validator;

  public <T> void validate(T dtoObject) throws ValidationException {
    try {
      Set<ConstraintViolation<T>> violations = validator.validate(dtoObject);
      if (!violations.isEmpty()) {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<T> constraintViolation : violations) {
          sb.append(constraintViolation.getMessage());
        }
        throw new ConstraintViolationException(violations);
      }
    } catch (Exception e) {
      throw new ValidationException("", e);
    }
  }

}
