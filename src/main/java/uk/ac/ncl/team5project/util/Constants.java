package uk.ac.ncl.team5project.util;

/**
 * @file Constants.java
 * @date 2025-04-01
 * @function_description: Utility class containing global constant values used throughout the application.
 * @interface_description: Defines role types used for JWT generation and role-based access control.
 * @calling_sequence: Accessed statically from service or filter layers.
 * @arguments_description: None
 * @list_of_subordinate_classes: None
 * @discussion: Centralizes role constant definitions to improve maintainability and readability.
 * @development_history: Created on 2025-04-01 as part of role and access control utility.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Holds application-wide constant definitions (e.g., user and admin roles).
 */
public class Constants {
    public static final String USER_ROLE = "user";
    public static final String ADMIN_ROLE = "admin";
}
