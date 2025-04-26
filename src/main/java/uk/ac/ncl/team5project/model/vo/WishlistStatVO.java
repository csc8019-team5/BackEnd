package uk.ac.ncl.team5project.model.vo;

import lombok.Data;
import uk.ac.ncl.team5project.entity.Book;

import java.util.List;
import java.util.Map;

/**
 * @file WishlistStatVO.java
 * @date 2025-04-19
 * @function_description: View Object for summarizing wishlist system statistics.
 * @interface_description: Contains the total number of wishlist entries and per-book wishlist counts.
 * @calling_sequence: WishlistMapper → WishlistStatVO → Controller → Frontend
 * @arguments_description:
 * - wishlistCount: Total number of wishlist records in the system
 * - bookCounts: List of BookCount objects representing how often each book was added to wishlists
 * @list_of_subordinate_classes: BookCount
 * @discussion: Used in /v1/wishlist/stat to support administrative analysis or reporting.
 * @development_history: Created on 2025-04-19 to support wishlist statistics endpoint.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-19
 * @modification_date: 2025-04-19
 * @description: Aggregates system-level statistics related to user wishlists.
 */

@Data
public class WishlistStatVO {
    private Long wishlistCount;
    private List<BookCount> bookCounts;
}
