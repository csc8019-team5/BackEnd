package uk.ac.ncl.team5project.form;

import java.util.Date;

import lombok.Data;

/**
 * @file AdminInsertForm.java
 * @date 2025-04-13 13:41
 * @function_description: Receive the administor information from front-end
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
    private String adminName;
    private String adminEmail;
    private String password;
    private Integer adminLevel;
    private Date registrationTime;
    private Date updateTime;
}
