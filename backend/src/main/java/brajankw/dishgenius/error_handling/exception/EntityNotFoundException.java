package brajankw.dishgenius.error_handling.exception;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 2091208377579749890L;

  public EntityNotFoundException(String message) {
    super(message);
  }
}
