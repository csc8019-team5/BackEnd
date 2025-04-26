package uk.ac.ncl.team5project.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @file Result.java
 * @date 2025-04-01
 * @function_description: Standard response wrapper for API results with status code, message, and data.
 * @interface_description: Provides static methods to return success or error responses.
 * @calling_sequence: Service → Controller → Frontend
 * @arguments_description: Integer code, String message, T data
 * @list_of_subordinate_classes: None
 * @discussion: Simplifies consistent API response structure across the system.
 * @development_history: Created on 2025-04-01 as part of common utility module.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Generic response format used by all endpoints to wrap success and error messages.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data){
        return new Result<>(200, "Success", data);
    }

    public static <T> Result<T> success(String message, T data){
        return new Result<>(200, message, data);
    }

    public static <T> Result<T> error(Integer code, String message){
        return new Result<>(code, message, null);
    }
}
