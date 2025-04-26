package uk.ac.ncl.team5project.service;

import org.springframework.http.ResponseEntity;
import uk.ac.ncl.team5project.entity.Wishlist;
import com.baomidou.mybatisplus.extension.service.IService;
import uk.ac.ncl.team5project.model.dto.WishlistAddDTO;
import uk.ac.ncl.team5project.util.Result;

/**
 * @file WishlistService.java
 * @date 2025-04-01
 * @function_description: Service interface for managing wishlist operations.
 * @interface_description: Includes methods for adding, retrieving (with pagination), and deleting wishlist items.
 * @calling_sequence: Controller → WishlistService → WishlistMapper
 * @arguments_description: WishlistAddDTO wishlist, Integer page, Integer size, Integer wishlistId
 * @list_of_subordinate_classes: WishlistAddDTO, Wishlist
 * @discussion: Provides core logic for the user's personal book wishlist management.
 * @development_history: Created on 2025-04-01 as part of wishlist feature module.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Defines service layer methods for wishlist CRUD operations.
 */
public interface WishlistService extends IService<Wishlist> {
    // Add a book to wishlist
    Result<?> addBookToWishlist(WishlistAddDTO wishlist);
    // Get wishlist with pagination
    Result<?> getWishlist(Integer page, Integer size);
    // Delete a book from wishlist
    Result<?> deleteBookFromWishlist(Integer wishlistId);

}
