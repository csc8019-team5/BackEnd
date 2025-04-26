package uk.ac.ncl.team5project.com.admin.Param;

import lombok.Data;
/**
 * @file BookInsertParam.java
 * @date 2025-04-13 23:56
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
public class BookInsertParam {
    private Integer bookId;
    private String name;
    private String category;
    private String author;
    private String publishingHouse;
    private String description;
    private String bookCover;
    private Boolean available;
}
