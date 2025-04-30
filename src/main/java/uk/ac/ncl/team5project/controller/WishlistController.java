package uk.ac.ncl.team5project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uk.ac.ncl.team5project.mapper.WishlistMapper;
import uk.ac.ncl.team5project.model.dto.WishlistAddDTO;
import uk.ac.ncl.team5project.model.vo.WishlistStatVO;
import uk.ac.ncl.team5project.service.WishlistService;
import uk.ac.ncl.team5project.util.Result;

/**
 * @file WishlistController.java
 * @date 2025-04-01
 * @function_description: Controller for wishlist management (add, query, delete)
 * @interface_description:
 * POST /v1/wishlist - Add a book to the wishlist
 * GET /v1/wishlist - Get current user's wishlist (supports pagination)
 * DELETE /v1/wishlist/{wishlist_id} - Delete a specific wishlist item
 * GET /v1/wishlist/stat - Get wishlist statistics (total entries, unique books)
 * @calling_sequence: Controller → WishlistService → WishlistMapper → Database
 * @arguments_description:
 * -WishlistAddDTO: DTO used for adding, includes user_id and book_id
 * -page, size: Optional pagination parameters
 * -wishlist_id: ID of the wishlist entry to be deleted
 * @list_of_subordinate_classes:
 * WishlistService, WishlistAddDTO, WishlistStatVO, Result
 * @discussion:
 * All endpoints require user authentication via JWT token.
 * @development_history:
 * Created on 2025-04-01 for wishlist feature
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-29
 * @modification_date: 2025-04-29
 * @description: REST controller for user wishlist management
 */
@RestController
@RequestMapping("/v1/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    /**
     * Adds a book to the current user's wishlist.
     * Accepts WishlistAddDTO in the request body.
     * Requires JWT authentication.
     */
    @PostMapping
    public Result<?> addBookToWishlist(@RequestBody WishlistAddDTO wishlist) {
        return wishlistService.addBookToWishlist(wishlist);
    }

    /**
     * Retrieves the current user's wishlist with pagination support.
     * Parameters: page (default 1), size (default 10)
     * Requires JWT authentication.
     */
    @GetMapping
    public Result<?> getWishlist(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return wishlistService.getWishlist(page, size);
    }

    /**
     * Removes a specific book from the wishlist by wishlist ID.
     * Path variable: wishlist_id
     * Requires JWT authentication.
     */
    @DeleteMapping("/{wishlist_id}")
    public Result<?> deleteBookFromWishlist(@PathVariable Integer wishlist_id) {
        return wishlistService.deleteBookFromWishlist(wishlist_id);
    }

    /**
     * Provides overall statistics for the wishlist system.
     * Returns: number of wishlist entries and how many distinct books are included.
     * Requires JWT authentication.
     */
    @Autowired
    private WishlistMapper wishlistMapper;
    @GetMapping("/stat")
    public Result<?> getWishlistStat() {
        WishlistStatVO wishlistStatVO = new WishlistStatVO();

        wishlistStatVO.setBookCounts(wishlistMapper.getBookCounts());

        wishlistStatVO.setWishlistCount(wishlistMapper.selectCount(null));

        wishlistStatVO.setBookCategoryCounts(wishlistMapper.getBookCategoryCounts());

        return Result.success(wishlistStatVO);
    }

}
