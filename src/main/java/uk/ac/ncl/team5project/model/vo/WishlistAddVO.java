package uk.ac.ncl.team5project.model.vo;

import lombok.Data;
import uk.ac.ncl.team5project.entity.Book;

/**
 * @file WishlistAddVO.java
 * @date 2025-04-01
 * @function_description: View Object for the response after adding a book to the wishlist.
 * @interface_description: Returns wishlist ID and the added book's basic information.
 * @calling_sequence: Service → Controller → Frontend
 * @arguments_description: Integer wishlist_id, Book book
 * @list_of_subordinate_classes: Book
 * @discussion: Used to confirm successful addition and display book info on the client side.
 * @development_history: Created on 2025-04-01 as part of wishlist functionality.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Encapsulates wishlist entry and associated book after an add operation.
 */
@Data
public class WishlistAddVO {
    private Integer wishlist_id;
    // The book that was added to the wishlist
    private Book book;
}
