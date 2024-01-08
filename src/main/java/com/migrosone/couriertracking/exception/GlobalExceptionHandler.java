package com.migrosone.couriertracking.exception;

import com.migrosone.couriertracking.payload.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller Advice for Rest Controllers.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GenericResponse<Object>> handle(Exception exception) {
        log.error("Request could not be processed: ", exception);
        GenericResponse<Object> response = GenericResponse.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GenericResponse<Object>> handleUnauthorized(Exception exception) {
        log.error("Request could not be processed: ", exception);
        GenericResponse<Object> response = GenericResponse.error("You are unauthorized for this request!");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GenericResponse<Object>> handleNotFound(Exception exception) {
        log.error("Request could not be processed: ", exception);
        GenericResponse<Object> response = GenericResponse.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GenericResponse<Object>> handleInvalidArgument(MethodArgumentNotValidException ex) {
        log.error("Request could not be processed: ", ex);

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, Object> errors = fieldErrors.stream()
                .collect(Collectors.toMap(FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() != null
                                ? fieldError.getDefaultMessage() : ""));

        GenericResponse<Object> response = GenericResponse.error("Validation failed.", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
