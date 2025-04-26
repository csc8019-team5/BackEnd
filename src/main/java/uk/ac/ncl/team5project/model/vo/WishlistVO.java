package uk.ac.ncl.team5project.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @file WishlistVO.java
 * @date 2025-04-01
 * @function_description: View Object for displaying the user's complete wishlist.
 * @interface_description: Contains a list of WishlistAddVO objects for frontend rendering.
 * @calling_sequence: Service → Controller → Frontend
 * @arguments_description: List<WishlistAddVO> wishlist
 * @list_of_subordinate_classes: WishlistAddVO
 * @discussion: Wraps multiple wishlist entries in a structured response format.
 * @development_history: Created on 2025-04-01 as part of wishlist viewing feature.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Response object for presenting the entire wishlist of a user.
 */
@Data
public class WishlistVO {
    // List of books in the user's wishlist
    private List<WishlistAddVO> wishlist;
}
