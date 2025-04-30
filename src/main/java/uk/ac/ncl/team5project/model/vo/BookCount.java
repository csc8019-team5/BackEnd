package uk.ac.ncl.team5project.model.vo;

import lombok.Data;

/**
 * @file BookCount.java
 * @date 2025-04-19
 * @function_description: View Object representing a book's frequency in user wishlists.
 * @interface_description:
 * Used in the wishlist statistics endpoint to report how many times each book (by name) was added.
 * @calling_sequence: Mapper → WishlistStatVO → Controller → Frontend
 * @arguments_description:
 * - String bookName: Name of the book.
 * - Integer count: Number of times this book was added to wishlists.
 * @list_of_subordinate_classes: None
 * @discussion:
 * Helps analyze book popularity based on wishlist count for recommendation or reporting.
 * @development_history: Created on 2025-04-19 for wishlist statistic aggregation.
 * @designer: Wensi Huang
 * @reviewer: Wensi Huang
 * @review_date: 2025-04-29
 * @modification_date: 2025-04-29
 * @description: Contains book name and the number of wishlist entries linked to that book.
 */

@Data
public class BookCount {
    private Integer count;
    private String bookName;
}
