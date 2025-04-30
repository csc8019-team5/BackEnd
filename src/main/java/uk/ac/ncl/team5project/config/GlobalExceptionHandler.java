package uk.ac.ncl.team5project.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uk.ac.ncl.team5project.util.Result;

/**
 * @file GlobalExceptionHandler.java
 * @date 2025-04-01
 * @function_description: Handles global exceptions including runtime and validation exceptions.
 * @interface_description: Exception handling methods with @ExceptionHandler annotation.
 * @calling_sequence: Controller → Exception thrown → Caught here → Return Result response
 * @arguments_description: Exception, MethodArgumentNotValidException
 * @list_of_subordinate_classes: Result
 * @discussion: Useful for unified error response formatting.
 * @development_history: Created on 2025-04-01 as part of global configuration module
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Provides centralized exception handling for REST controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all unexpected exceptions.
     * Prints stack trace and returns a 500 error with exception message.
     * @param e the caught exception
     * @return standardized error result
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        e.printStackTrace();
        String message = e.getMessage();
        
       
        if (message != null && message.contains("not available")) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Result.error(409, "Book is not available"));
        }
        
        
        if (message != null && (message.contains("loan limit") || message.contains("maximum"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Result.error(400, "Maximum loan limit reached"));
        }
        
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.error(500, message != null ? message : "Internal server error"));
    }

    /**
     * Handles validation exceptions triggered by @Valid annotations.
     * Extracts first field error and returns message.
     * @param e validation exception
     * @return result with 400 status and error message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (fieldError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Result.error(400, fieldError.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Result.error(400, "Validation failed"));
    }
}
