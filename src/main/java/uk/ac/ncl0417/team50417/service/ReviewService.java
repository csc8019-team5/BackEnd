package uk.ac.ncl0417.team50417.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import uk.ac.ncl0417.team50417.Mapper.ReviewMapper;
import uk.ac.ncl0417.team50417.entities.Review;
import java.util.List;

/**
 * Class: ReviewService
 * File: ReviewService.java
 * Created on: 2025/4/25
 * Author: menghui yao
 *
 * Description:
 * <pre>
 *     Function: Service layer for handling review-related business logic. This class interacts with
 *               the ReviewMapper to perform operations on review data in the database.
 *     Interface Description:
 *         - selectReviewByBookId: Retrieves all reviews for a specified book using its ID.
 *         - selectReviewByUserId: Retrieves all reviews posted by a specified user using their ID.
 *         - updateReviewByBookIdUserId: Updates an existing review or creates a new one based on
 *           the user ID and book ID.
 *         - deleteReviewByBookIdUserId: Deletes a review specified by user ID and book ID.
 *     Calling Sequence:
 *         - This service layer is called by the controller layer to manage reviews for books and users.
 *     Argument Description:
 *         - id (Integer): Represents the book or user ID used for fetching or managing reviews.
 *         - userId (Integer): The ID of the user who posted the review.
 *         - bookId (Integer): The ID of the book being reviewed.
 *         - content (String): The content of the review.
 *     List of Subordinate Classes: ReviewMapper.
 * </pre>
 * Development History:
 * <pre>
 *     Designer: menghui yao
 *     Reviewer: menghui yao
 *     Review Date: 2025/4/25
 *     Modification Date: 2025/4/25
 *     Modification Description: none
 * </pre>
 */

@Service
public class ReviewService {

    @Resource
    private ReviewMapper ReviewMapper;

    public List<Review> selectReviewByBookId(Integer id) {
        return ReviewMapper.selectReviewByBookId(id);

    }

    public List<Review> selectReviewByUserId(Integer id) {
        return ReviewMapper.selectReviewByUserId(id);
    }

    /**
     * here updateReviewByBookIdUserId method contains if condition,
     * first to check in DB table whether exists userid+bookid review.
     * if existing, use update mapper method, else use insert new one.
     */
    public void updateReviewByBookIdUserId(Integer userId, Integer bookId, String content) {
        ReviewMapper.updateReviewByBookIdUserId(userId, bookId, content);

        // searching in table to find whether exist
        List<Review> existingReview = ReviewMapper.selectReviewByUserIdAndBookId(userId, bookId);

        if (existingReview != null && !existingReview.isEmpty()) {
            // true，update
            ReviewMapper.updateReviewByBookIdUserId(userId, bookId, content);
        } else {
            // false, insert
            ReviewMapper.insertReviewByBookIdUserId(userId, bookId, content);
        }
    }
    public void deleteReviewByBookIdUserId(Integer userId, Integer bookId) {
        ReviewMapper.deleteReviewByBookIdUserId(userId,bookId);
    }
}

