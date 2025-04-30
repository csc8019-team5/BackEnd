package uk.ac.ncl.team5project.model.vo;

import lombok.Data;
import uk.ac.ncl.team5project.entity.Book;

import java.util.List;
import java.util.Map;

/**
 * @file WishlistStatVO.java
 * @date 2025-04-19
 * @function_description: View Object for summarizing wishlist system statistics.
 * @interface_description:
 * Used in the /v1/wishlist/stat endpoint to return:
 * - Total number of wishlist records
 * - Count of how many times each book was added
 * - Count of how many times each book category was added
 * @calling_sequence: WishlistMapper → WishlistStatVO → Controller → Frontend
 * @arguments_description:
 * - wishlistCount: Total number of wishlist records in the system
 * - bookCounts: List of BookCount objects representing how often each book was added
 * - bookCategoryCounts: List of BookCategoryCount objects showing category-level addition counts
 * @list_of_subordinate_classes: BookCount, BookCategoryCount
 * @discussion:
 * Supports backend statistics display and administrative analysis related to book wishlist popularity.
 * @development_history: Created on 2025-04-19 to support wishlist statistics aggregation.
 * @designer: Wensi Huang
 * @reviewer: Wensi Huang
 * @review_date: 2025-04-29
 * @modification_date: 2025-04-29
 * @description: Aggregates system-level statistics related to user wishlists,
 * including per-book and per-category addition counts.
 */


@Data
public class WishlistStatVO {
    private Long wishlistCount;
    private List<BookCount> bookCounts;
    private List<BookCategoryCount> bookCategoryCounts;
}