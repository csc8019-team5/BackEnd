package uk.ac.ncl.team5project.model.vo;

import lombok.Data;
import uk.ac.ncl.team5project.entity.Book;

import java.util.List;

/**
 * @file UserInfoVO.java
 * @date 2025-04-01
 * @function_description: View Object representing a summary of user profile and activity data.
 * @interface_description: Includes basic user info, borrowing stats, and wishlist content.
 * @calling_sequence: Service → Controller → Frontend
 * @arguments_description: Integer userId, String userName, String userEmail, Integer borrowedCount, List<Book> borrowedBooks, List<Book> wishlist
 * @list_of_subordinate_classes: Book
 * @discussion: Combines multiple data sources to build a comprehensive user profile overview.
 * @development_history: Created on 2025-04-01 as part of user info response structure.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Contains all relevant user information including borrowed books and wishlist.
 */
@Data
public class UserInfoVO {
    private Integer userId;
    private String userName;
    private String userEmail;
    private Integer borrowedCount;
    private List<Book> borrowedBooks;
    // List of books in the user's wishlist
    private List<Book> wishlist;
}
