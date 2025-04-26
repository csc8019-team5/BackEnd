package uk.ac.ncl.team5project.model.vo;

import lombok.Data;

/**
 * @file UpdateVO.java
 * @date 2025-04-01
 * @function_description: View Object for profile update responses.
 * @interface_description: Returns updated user information and a message after profile update.
 * @calling_sequence: Service → Controller → Frontend
 * @arguments_description: Integer user_id, String user_name, String user_email, String message
 * @list_of_subordinate_classes: None
 * @discussion: Provides updated user data and status message after modifying profile information.
 * @development_history: Created on 2025-04-01 as part of user update response structure.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Encapsulates result data from user information update operation.
 */
@Data
public class UpdateVO {
    private Integer user_id;
    private String user_name;
    private String user_email;
    private String message;
}
