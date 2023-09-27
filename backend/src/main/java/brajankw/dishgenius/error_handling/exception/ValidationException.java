package brajankw.dishgenius.error_handling.exception;

import java.io.Serial;

public class ValidationException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 7944419434412892868L;

  public ValidationException(String message) {
    super(message);
  }
}
