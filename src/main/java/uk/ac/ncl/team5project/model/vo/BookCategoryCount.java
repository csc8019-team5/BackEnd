package uk.ac.ncl.team5project.model.vo;

import lombok.Data;

/**
 * @file BookCategoryCount.java
 * @date 2025-04-19
 * @function_description: View Object representing the frequency of each book category in user wishlists.
 * @interface_description:
 * Used in the wishlist statistics endpoint to report how many times books of each category were added to wishlists.
 * @calling_sequence: Mapper → WishlistStatVO → Controller → Frontend
 * @arguments_description:
 * - String category: The category name.
 * - Integer count: Number of times this category appears in the wishlist.
 * @list_of_subordinate_classes: None
 * @discussion:
 * Helps analyze which book categories are most favored by users, supporting recommendations and reporting.
 * @development_history: Created on 2025-04-19 for wishlist category statistics aggregation.
 * @designer: Wensi Huang
 * @reviewer: Wensi Huang
 * @review_date: 2025-04-29
 * @modification_date: 2025-04-29
 * @description: Contains category name and the number of wishlist entries linked to that category.
 */

@Data
public class BookCategoryCount {
    private Integer count;
    private String category;
}
