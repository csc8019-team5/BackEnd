package uk.ac.ncl.team5project.config;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error(500, e.getMessage());
    }

    /**
     * Handles validation exceptions triggered by @Valid annotations.
     * Extracts first field error and returns message.
     * @param e validation exception
     * @return result with 400 status and error message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        if(fieldError != null){
            return Result.error(400, fieldError.getDefaultMessage());
        }
        return Result.error(400, "参数验证失败");
    }
}
