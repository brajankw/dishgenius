package brajankw.dishgenius.error_handling.exception;

import java.io.Serial;

public class BadRequestException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 5313097805366991400L;

  public BadRequestException(String message) {
    super(message);
  }
}
