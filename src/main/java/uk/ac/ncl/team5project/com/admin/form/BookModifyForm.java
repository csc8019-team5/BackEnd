package uk.ac.ncl.team5project.com.admin.form;

import lombok.Data;

/**
 * @file BookModifyForm.java
 * @date 2025-04-13 19:26
 * @function_description: 
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
public class BookModifyForm {
    private Integer bookId;
    private String name;
    private String category;
    private String author;
    private String publishingHouse;
    private String description;
    private String bookCover;
    private Integer available;
}
