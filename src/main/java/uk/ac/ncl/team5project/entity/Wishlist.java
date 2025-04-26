package uk.ac.ncl.team5project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @file Wishlist.java
 * @date 2025-04-01
 * @function_description: Entity class representing a user's book wishlist entry.
 * @interface_description: Maps user ID and book ID to the WISHLIST table.
 * @calling_sequence: MyBatis-Plus → WishlistMapper → WISHLIST table
 * @arguments_description: None
 * @list_of_subordinate_classes: None
 * @discussion: Used to track books favorited by users for future borrowing or interest.
 * @development_history: Created on 2025-04-01 as part of wishlist feature module.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Stores book entries that users have added to their wishlist.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("WISHLIST")
public class Wishlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer bookId;

}
