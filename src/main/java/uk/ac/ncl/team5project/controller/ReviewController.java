package uk.ac.ncl.team5project.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import uk.ac.ncl.team5project.config.Result;
import uk.ac.ncl.team5project.entities.Review;
import uk.ac.ncl.team5project.service.ReviewService;
import java.util.List;

/**
 * Class: ReviewController
 * File: ReviewController.java
 * Created on: 2025/4/25
 * Author: menghui yao
 *
 * Description:
 * <pre>
 *     Function: Provides endpoints for managing reviews
 *               including viewing, creating, updating, and deleting reviews for books by users.
 *     Interface Description:
 *         - Calling Sequence:
 *                          selectReviewByBookId: Fetch all reviews associated with a book by its ID.
 *                          selectReviewByUserId: Fetch all reviews associated with a user by their ID.
 *                          updateReviewByBookIdUserId: Create a new review or update an existing review for a specific book by a specific user.
 *                          deleteReviewByBookIdUserId: Delete a review for a specific book by a specific user.
 *         - Argument Description:
 *                          id (Integer): Book or User ID used for fetching or managing reviews.
 *                          bookId (Integer): The ID of the book.
 *                          userId (Integer): The ID of the user.
 *                          content (String): The content of the review.
 *                          pageNum (Integer): The page number for pagination (optional, defaults to 1).
 *                          pageSize (Integer): The number of reviews per page (optional, defaults to 3).
 *         - List of Subordinate Classes:
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: menghui yao
 *     Reviewer: menghui yao
 *     Review Date: 2025/4/25
 *     Modification Date: 2025/4/25
 *     Modification Description: none
 * </pre>
 */
@RestController
//@RestController = @Controller + @ResponseBody
@RequestMapping("v1/reviews")
public class ReviewController {
    @Resource
    private ReviewService reviewService;

    /**
     * Fetches all reviews associated with a specific book by its ID.
     * This endpoint returns a paginated list of reviews for a book.
     * @param id The ID of the book whose reviews are to be fetched.
     * @param pageNum The page number for pagination. Defaults to 1 if not provided.
     * @param pageSize The number of reviews per page. Defaults to 3 if not provided.
     * @return A Result containing a PageInfo object that contains the list of reviews for the book.
     * @throws Result.error() If the book ID is null or if an error occurs while fetching reviews.
     */

    @GetMapping("selectReviewByBookId")
    //through http://localhost:8080/v1/reviews/selectReviewByBookId?id=123[&pageNum=1&pageSize=3] query
    public Result selectReviewByBookId(
                                     @RequestParam Integer id,
                                     @RequestParam(defaultValue = "1") Integer pageNum,   // default is 1st page
                                     @RequestParam(defaultValue = "3") Integer pageSize) { // default is 3 per page
        try {
            if (id == null) {
                return Result.error("id is null");
            }
            // PageHelper
            PageMethod.startPage(pageNum, pageSize);
            List<Review> review = reviewService.selectReviewByBookId(id); // query result of all reviews
            // put page info and reviews together
            PageInfo<Review> pageInfo = new PageInfo<>(review);
            // return above information
            return Result.success(pageInfo);
        } catch (Exception e) { return Result.error("error：" + e.getMessage());}
    }

    /**
     * this is for show specific user's all reviews
     * http://localhost:8080/v1/reviews/selectReviewByUserId?id=123[&pageNum=1&pageSize=3], []means optional
     * Fetches all reviews associated with a specific user by their ID.
     * This endpoint returns a paginated list of reviews for a user.
     * @param id The ID of the user whose reviews are to be fetched.
     * @param pageNum The page number for pagination. Defaults to 1 if not provided.
     * @param pageSize The number of reviews per page. Defaults to 3 if not provided.
     * @return A Result containing a PageInfo object that contains the list of reviews for the user.
     * @throws Result.error() If the user ID is null or if an error occurs while fetching reviews.
     */
    @GetMapping("selectReviewByUserId")
    public Result selectReviewByUserId(
            @RequestParam Integer id,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "3") Integer pageSize) {
        if (id == null) {
            return Result.error();
        }
        //  PageHelper
        PageMethod.startPage(pageNum, pageSize);
        List<Review> review = reviewService.selectReviewByUserId(id);
        // put page info and reviews together
        PageInfo<Review> pageInfo = new PageInfo<>(review);
        return Result.success(pageInfo);
    }

    /**
     * Fetches all reviews associated with a specific user by their ID.
     * <p>
     * This endpoint returns a paginated list of reviews for a user.
     * </p>
     *
     * @param id The ID of the user whose reviews are to be fetched.
     * @param pageNum The page number for pagination. Defaults to 1 if not provided.
     * @param pageSize The number of reviews per page. Defaults to 3 if not provided.
     * @return A {@link Result} containing a {@link PageInfo} object that contains the list of reviews for the user.
     *
     * @throws Result.error() If the user ID is null or if an error occurs while fetching reviews.
     */
    @PostMapping("updateReviewByBookIdUserId")
    public Result updateReviewByBookIdUserId(@RequestParam Integer bookId,
                              @RequestParam Integer userId,
                              @RequestBody String content) {
        try {
            reviewService.updateReviewByBookIdUserId(userId, bookId, content);
            return Result.success();
        } catch (Exception e) {
            return Result.error("error：" + e.getMessage());
        }
    }

    /**
     * Deletes a review for a specific book by a specific user.
     * <p>
     * This endpoint deletes the review associated with the specified book and user.
     * </p>
     *
     * @param bookId The ID of the book whose review is to be deleted.
     * @param userId The ID of the user who submitted the review.
     * @return A {@link Result} indicating the success or failure of the operation.
     *
     * @throws Result.error() If an error occurs while deleting the review.
     */
    @DeleteMapping("deleteReviewByBookIdUserId")
    public Result deleteReviewByBookIdUserId(@RequestParam Integer bookId,
                                             @RequestParam Integer userId) {
        try {
            reviewService.deleteReviewByBookIdUserId(userId, bookId);
            return Result.success();  // success return code 200
        } catch (Exception e) {
            return Result.error("error：" + e.getMessage());  // failed return code 500
        }
    }

}
