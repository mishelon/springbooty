package com.mishelon.base.exceptions;

/**
 * @author mishelon
 * @since 26 mar 2022
 */


public class ValidationException extends Exception {
  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }
}
