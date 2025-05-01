package uk.ac.ncl.team5project.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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