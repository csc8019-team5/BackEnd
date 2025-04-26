package uk.ac.ncl.team5project.model.dto;

import lombok.Data;

/**
 * @file WishlistAddDTO.java
 * @date 2025-04-01
 * @function_description: Data Transfer Object for adding a book to the user's wishlist.
 * @interface_description: Carries book ID to be added to the wishlist.
 * @calling_sequence: Frontend → Controller → Service
 * @arguments_description: Integer book_id
 * @list_of_subordinate_classes: None
 * @discussion: Simplifies transfer of book ID when adding to wishlist.
 * @development_history: Created on 2025-04-01 as part of wishlist add functionality.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: DTO used to encapsulate a book ID for wishlist addition.
 */
@Data
public class WishlistAddDTO {
    private Integer book_id;
}
