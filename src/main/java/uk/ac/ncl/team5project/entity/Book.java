package uk.ac.ncl.team5project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @file Book.java
 * @date 2025-04-01
 * @function_description: Entity class representing the BOOK table in the database.
 * @interface_description: Includes book ID, name, publisher, description, and cover image.
 * @calling_sequence: MyBatis-Plus → BookMapper → BOOK table
 * @arguments_description: None
 * @list_of_subordinate_classes: None
 * @discussion: Used for book catalog, wishlist, and borrowing modules.
 * @development_history: Created on 2025-04-01 as part of book entity setup.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Maps book-related fields to the BOOK database table.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "book_id", type = IdType.AUTO)
    private Integer bookId;

    private String name;

    private String publishingHouse;

    private String description;

    private String bookCover;


}
