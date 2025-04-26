package uk.ac.ncl.team5project.com.admin.dto.form;

import java.time.LocalDate;

import lombok.Data;

/**
 * @file AdminInfoDto.java
 * @date 2025-04-10 10:38
 * @function_description: This class only store necessary field of entity to avoid expose some important 
 * @interface_description: 
 *     @calling_sequence: 
 *     @arguments_description: 
 *     @list_of_subordinate_classes: 
 * @discussion: 
 * @development_history: 
 *     @designer Qingyu Cao 
 *     @reviewer: 
 *     @review_date: 
 *     @modification_date: 
 *     @description: 
 */

 @Data
public class AdminInsertForm {
    Integer adminId;
    String adminName;
    String adminEmail;
    Integer adminLevel;
    LocalDate registrationTime;
    LocalDate updateTime;
}
