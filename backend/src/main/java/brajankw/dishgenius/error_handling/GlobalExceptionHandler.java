package brajankw.dishgenius.error_handling;

import brajankw.dishgenius.error_handling.exception.AccessDeniedException;
import brajankw.dishgenius.error_handling.exception.AuthenticationException;
import brajankw.dishgenius.error_handling.exception.BadRequestException;
import brajankw.dishgenius.error_handling.exception.EntityNotFoundException;
import brajankw.dishgenius.error_handling.exception.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
    log.error(AccessDeniedException.class.getName() + " status: " + HttpStatus.FORBIDDEN.value());

    return new ResponseEntity<>("You are not authorize to see this content.",
        HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler({
      BadRequestException.class,
      ValidationException.class
  })
  public ResponseEntity<String> handleBadRequestException(
      Exception ex) {
    log.error(ex.getMessage() + " status: " + HttpStatus.BAD_REQUEST.value());

    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex, HttpServletRequest request) {
    log.error("error while validating status: " + HttpStatus.BAD_REQUEST.value());

    List<String> errors = new ArrayList<>();
    ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
    Map<String, List<String>> result = new HashMap<>();
    result.put("errors", errors);

    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleEntityNotFoundException(
      EntityNotFoundException ex) {
    log.error(ex.getMessage() + " status: " + HttpStatus.NOT_FOUND.value());

    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<String> handleAuthenticationException(
      AuthenticationException ex) {
    log.error(AuthenticationException.class.getName() + " status: " + HttpStatus.UNAUTHORIZED.value());

    return new ResponseEntity<>("You must log in to your account.",
        HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleAllExceptions(
      Exception ex) {
    log.error(ex.getMessage() + " status: " + HttpStatus.INTERNAL_SERVER_ERROR.value());

    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
