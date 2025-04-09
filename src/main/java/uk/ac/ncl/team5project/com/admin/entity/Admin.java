package uk.ac.ncl.team5project.com.admin.entity;
/**
 * @file Admin.java
 * @date 2025-04-09 02:58
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

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    Integer adminId;
    String adminName;
    String adminEmail;
    String password;
    Integer adminLevel;
    LocalDate registrationTime;
    LocalDate updateTime;
}
