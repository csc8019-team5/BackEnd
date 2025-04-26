package uk.ac.ncl0417.team50417.Mapper;

import org.apache.ibatis.annotations.*;
import uk.ac.ncl0417.team50417.entities.Review;
import java.util.List;
/**
 * Class: ReviewMapper
 * File: ReviewMapper.java
 * Created on: 2025/4/25
 * Author: menghui yao
 *
 * Description:
 * <pre>
 *     Function: Provides database interaction methods for handling reviews.
 *     Interface Description:
 *         - selectReviewByBookId: Fetch all reviews for a specific book using its ID.
 *         - selectReviewByUserId: Fetch all reviews posted by a specific user using their ID.
 *         - selectReviewByUserIdAndBookId: Fetch a review posted by a specific user for a specific book.
 *         - updateReviewByBookIdUserId: Update the content of an existing review for a specific book and user.
 *         - deleteReviewByBookIdUserId: Delete an existing review for a specific book and user.
 *         - insertReviewByBookIdUserId: Insert a new review for a specific book and user.
 *     Calling Sequence:
 *         - These methods are called by the service layer to interact with the database for managing reviews.
 *     Argument Description:
 *         - id (Integer): Represents the book or user ID used for fetching or managing reviews.
 *         - userId (Integer): The ID of the user who posted the review.
 *         - bookId (Integer): The ID of the book being reviewed.
 *         - content (String): The content of the review.
 *     List of Subordinate Classes: None.
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

@Mapper
public interface ReviewMapper {
    @Select("select * from REVIEWS where book_id =#{id}")
    List<Review> selectReviewByBookId(Integer id);

    @Select("select * from REVIEWS where user_id=#{id}")
    List<Review> selectReviewByUserId(Integer id);

    @Select("SELECT * FROM REVIEWS WHERE user_id = #{userId} AND book_id = #{bookId}")
    List<Review> selectReviewByUserIdAndBookId(Integer userId, Integer bookId);

    @Update("UPDATE REVIEWS SET review_content = #{content} WHERE user_id = #{userId} AND book_id = #{bookId}")
    void updateReviewByBookIdUserId(Integer userId, Integer bookId, String content);

    @Delete("DELETE FROM REVIEWS WHERE user_id = #{userId} AND book_id = #{bookId}")
    void deleteReviewByBookIdUserId(Integer userId, Integer bookId);

    @Insert("INSERT INTO REVIEWS (user_id, book_id, review_content) VALUES (#{userId}, #{bookId}, #{content})")
    void insertReviewByBookIdUserId(Integer userId, Integer bookId, String content);
}
