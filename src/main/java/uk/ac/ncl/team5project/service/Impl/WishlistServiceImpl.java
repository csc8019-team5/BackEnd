package uk.ac.ncl.team5project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uk.ac.ncl.team5project.entity.User;
import uk.ac.ncl.team5project.entity.Wishlist;
import uk.ac.ncl.team5project.mapper.UserMapper;
import uk.ac.ncl.team5project.mapper.WishlistMapper;
import uk.ac.ncl.team5project.model.dto.WishlistAddDTO;
import uk.ac.ncl.team5project.model.vo.WishlistAddVO;
import uk.ac.ncl.team5project.service.WishlistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import uk.ac.ncl.team5project.util.Result;

import java.util.List;

/**
 * @file WishlistServiceImpl.java
 * @date 2025-04-01
 * @function_description:
 * Service implementation for wishlist-related operations including add, query (with pagination), and delete.
 * @interface_description:
 * - addBookToWishlist(WishlistAddDTO wishlist): Add a book to the user's wishlist
 * - getWishlist(Integer page, Integer size): Get wishlist items with pagination
 * - deleteBookFromWishlist(Integer wishlistId): Remove a book from the wishlist
 * @calling_sequence: Controller → WishlistServiceImpl → WishlistMapper → Database
 * @arguments_description:
 * - WishlistAddDTO: Data Transfer Object used for adding wishlist entries
 * - page/size: Pagination parameters from controller
 * - wishlistId: The ID of the wishlist record to delete
 * @list_of_subordinate_classes: UserMapper, WishlistMapper, WishlistAddDTO, WishlistAddVO
 * @discussion: All methods require authentication; user is resolved via JWT token (email → user_id).
 * @development_history: Created on 2025-04-01 as part of wishlist module
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Implements the core logic of the wishlist service.
 */
@Service
public class WishlistServiceImpl extends ServiceImpl<WishlistMapper, Wishlist> implements WishlistService {
    @Autowired
    private UserMapper userMapper;

    /**
     * Adds a book to the current user's wishlist.
     * Checks if the book already exists in the wishlist.
     * @param wishlist DTO containing book_id
     * @return WishlistAddVO if added successfully; error message otherwise
     */
    @Override
    public Result<?> addBookToWishlist(WishlistAddDTO wishlist) {
        // Get current authenticated user's email from JWT
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();

        // Retrieve user from database
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_email", email));
        if (user == null) {
            return Result.error(500,"User does not exist");
        }
        Wishlist existingWishlist = baseMapper.selectOne(new QueryWrapper<Wishlist>().eq("user_id", user.getUserId())
                .eq("book_id", wishlist.getBook_id()));
        if (existingWishlist != null) {
            return Result.error(500,"This book is already in the wishlist");
        }

        Wishlist newWishlist = new Wishlist();
        newWishlist.setUserId(user.getUserId());
        newWishlist.setBookId(wishlist.getBook_id());
        boolean save = save(newWishlist);
        if (save) {
            WishlistAddVO wishlistAddVO = new WishlistAddVO();
            wishlistAddVO.setWishlist_id(newWishlist.getId());
            return Result.success(wishlistAddVO);
        }
        return Result.error(500,"Failed to add to wishlist");
    }

    /**
     * Retrieves the current user's wishlist with pagination support.
     * @param page current page number
     * @param size number of items per page
     * @return List of Wishlist entries for the current page
     */
    @Override
    public Result<?> getWishlist(Integer page, Integer size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_email", email));
        if (user == null) {
            return Result.error(500,"User does not exist");
        }
        Page<Wishlist> wishlistPage = new Page<>(page, size);
        Page<Wishlist> selectPage = baseMapper.selectPage(wishlistPage, new QueryWrapper<Wishlist>().eq("user_id", user.getUserId()));

        if (selectPage!= null) {
            return Result.success(selectPage.getRecords());
        }
        return Result.error(500,"Failed to retrieve wishlist");
    }

    /**
     * Deletes a book from the current user's wishlist.
     * @param wishlistId ID of the wishlist entry to delete
     * @return success or error message
     */
    @Override
    public Result<?> deleteBookFromWishlist(Integer wishlistId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_email", email));
        if (user == null) {

            return Result.error(500,"User does not exist");
        }
        Wishlist wishlist = baseMapper.selectById(wishlistId);

        if (wishlist == null) {
            return Result.error(500,"Wishlist item does not exist");
        }
        boolean remove = removeById(wishlist);
        if (remove) {
            return Result.success("Deleted successfully",null);
        }
        return Result.error(500,"Failed to delete wishlist item");
    }
}
