package uk.ac.ncl.team5project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Class: GlobalExceptionHandler
 * File: GlobalExceptionHandler.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Provides centralized exception handling for the application, converting
 *               exceptions into standardized API responses. Ensures consistent error reporting
 *               across all controllers.
 *     Interface Description:
 *         - handleRuntimeException: Handles general runtime exceptions.
 *         - handleResourceNotFoundException: Handles cases where requested resources are not found.
 *         - handleBadRequestException: Handles invalid request parameters or data.
 *     Calling Sequence:
 *         - Automatically invoked by Spring Framework when exceptions are thrown during request processing.
 *         - Example: When a book doesn't exist, returns formatted error response to client.
 *     Argument Description:
 *         - exception: The exception that was thrown during request processing.
 *         - request: The HTTP request that led to the exception.
 *     List of Subordinate Classes: None.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Initial implementation with standardized error responses.
 * </pre>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles RuntimeException by converting to standardized error response.
     * 
     * @param ex The runtime exception that was thrown
     * @return Response entity with error message and 400 status code
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
} 