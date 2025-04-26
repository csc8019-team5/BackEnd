package uk.ac.ncl.team5.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
/**
 * Class: Review
 * File: Review.java
 * Created on: 2025/4/25
 * Author: menghui yao
 *
 * Description:
 * <pre>
 *     Function: Represents a review for a book, including review content, the book being reviewed,
 *               the user who submitted the review, and relevant timestamps.
 *     Interface Description:
 *         - reviewId (Integer): The unique identifier of the review.
 *         - bookId (Integer): The ID of the book being reviewed.
 *         - reviewContent (String): The content of the review.
 *         - createdTime (Date): The timestamp when the review was created or last updated.
 *         - userId (Integer): The ID of the user who submitted the review.
 *         - userName (String): The name of the user who submitted the review.
 *         - name (String): The name of the book being reviewed.
 *     Calling Sequence:
 *         - This class is used by the Review service to represent the review entity in database interactions
 *           and transfer the data between layers in the application.
 *     Argument Description:
 *         - reviewId: A unique identifier for each review.
 *         - bookId: The ID of the book associated with the review.
 *         - reviewContent: The text content of the review written by the user.
 *         - createdTime: The time when the review was created or last modified, automatically updated in the database.
 *         - userId: The unique identifier for the user who posted the review.
 *         - userName: The name of the user who posted the review.
 *         - name: The title of the book being reviewed.
 *     List of Subordinate Classes: None.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: menghui yao
 *     Reviewer: menghui yao
 *     Review Date: 2025/4/25
 *     Modification Date: 2025/4/25
 *     Modification Description: 2025/4/25
 * </pre>
 */
@Data
public class Review {
    private Integer reviewId;
    private Integer bookId;
    private String reviewContent;
    // created_time will change when update or created because the formation is DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP shown in database
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // formatted yyyy-MM-dd'T'HH:mm:ss
    private Date createdTime;
    private Integer userId;
    private String userName;//user name
    private String name;// book name
}
