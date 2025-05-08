package uk.ac.ncl.team5project.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
/**
 * Class: UserBook
 * File: UserBook.java
 * Created on: 2025/5/8
 * Author: Menghui Yao
 *
 * Description:
 * <pre>
 *     Function: Represents a book borrowing record linking users to books with loan period details.
 *     Interface Description:
 *         - Calling Sequence:
 *                          getId/setId: Gets/Sets the record identifier
 *                          getUserId/setUserId: Gets/Sets the associated user ID
 *                          getBookId/setBookId: Gets/Sets the associated book ID
 *                          getBorrowDate/setBorrowDate: Gets/Sets the borrowing date (auto-initialized to current date)
 *                          getReturnDateEstimated/setReturnDateEstimated: Gets/Sets the estimated return date (auto-initialized to 1 month from now)
 *                          getReturnDate/setReturnDate: Gets/Sets the actual return date
 *                          toString: Returns string representation of the borrowing record
 *         - Argument Description:
 *                          id (Integer): Primary key of the borrowing record
 *                          userId (Integer): Foreign key referencing the user
 *                          bookId (Integer): Foreign key referencing the book
 *                          borrowDate (Date): Date when book was borrowed (default: current date)
 *                          returnDateEstimated (Date): Estimated return date (default: 1 month after borrow date)
 *                          returnDate (Date): Actual return date (null if not yet returned)
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Menghui Yao
 *     Reviewer: Menghui Yao
 *     Review Date: 2025/5/8
 *     Modification Date: 2025/5/8
 *     Modification Description: Initial implementation of book borrowing record entity
 * </pre>
 */
public class UserBook {
    private Integer id;

    private Integer userId;

    private Integer bookId;

    private Date borrowDate = new Date();

    private Date returnDateEstimated = Date.from(
    LocalDate.now().plusMonths(1)
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
);

    private Date returnDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDateEstimated() {
        return returnDateEstimated;
    }

    public void setReturnDateEstimated(Date returnDateEstimated) {
        this.returnDateEstimated = returnDateEstimated;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", bookId=").append(bookId);
        sb.append(", borrowDate=").append(borrowDate);
        sb.append(", returnDateEstimated=").append(returnDateEstimated);
        sb.append(", returnDate=").append(returnDate);
        sb.append("]");
        return sb.toString();
    }
}