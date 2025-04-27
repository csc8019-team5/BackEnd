package uk.ac.ncl.team5project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @file User.java
 * @date 2025-04-01
 * @function_description: Entity class representing registered users of the system.
 * @interface_description: Maps user ID, name, email, password, and timestamps to USER table.
 * @calling_sequence: MyBatis-Plus → UserMapper → USER table
 * @arguments_description: None
 * @list_of_subordinate_classes: None
 * @discussion: Used for authentication, authorization, and profile data storage.
 * @development_history: Created on 2025-04-01 as part of user entity setup.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Represents system users in the database with credentials and timestamps.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userEmail;

    private String password;

    private LocalDateTime registrationTime;

    private LocalDateTime updateTime;


}
