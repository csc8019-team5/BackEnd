package uk.ac.ncl.team5project.com.admin.dto.param;

import java.time.LocalDate;

import lombok.Data;

/**
 * @file AdminInsertParam.java
 * @date 2025-04-10 23:55
 * @function_description: 
 * @interface_description: 
 *     @calling_sequence: 
 *     @arguments_description: 
 *     @list_of_subordinate_classes: 
 * @discussion: 
 * @development_history: 
 *     @designer USER 
 *     @reviewer: 
 *     @review_date: 
 *     @modification_date: 
 *     @description: 
 */

@Data
public class AdminInsertParam {
    Integer adminId;
    String adminName;
    String adminEmail;
    String password;
    Integer adminLevel;
    LocalDate registrationTime;
    LocalDate updateTime;
}
