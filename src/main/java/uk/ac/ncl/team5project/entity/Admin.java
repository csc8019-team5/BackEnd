package uk.ac.ncl.team5project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @file Admin.java
 * @date 2025-04-01
 * @function_description: Entity class representing the ADMIN table in the database.
 * @interface_description: Contains admin user details including ID, email, password, and timestamps.
 * @calling_sequence: MyBatis-Plus → AdminMapper → ADMIN table
 * @arguments_description: None
 * @list_of_subordinate_classes: None
 * @discussion: Used for administrator authentication and authorization management.
 * @development_history: Created on 2025-04-01 as part of the admin management module.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Maps fields to the ADMIN database table for use with MyBatis-Plus.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ADMIN")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    private String adminName;

    private String adminEmail;

    private String password;

    private Integer adminLevel;

    private LocalDateTime registrationTime;

    private LocalDateTime updateTime;


}
