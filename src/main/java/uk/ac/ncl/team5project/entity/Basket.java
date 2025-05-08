package uk.ac.ncl.team5project.entity;
/**
 * Class: Basket
 * File: Basket.java
 * Created on: 2025/5/8
 * Author: Menghui Yao
 *
 * Description:
 * <pre>
 *     Function: Represents a shopping basket entity that associates users with books.
 *     Interface Description:
 *         - Calling Sequence:
 *                          getId/setId: Gets/Sets the unique basket identifier
 *                          getUserId/setUserId: Gets/Sets the associated user ID
 *                          getBookId/setBookId: Gets/Sets the associated book ID
 *                          getIsValid/setIsValid: Gets/Sets the validity status
 *                          toString: Returns string representation of basket
 *         - Argument Description:
 *                          id (Integer): Primary key of the basket
 *                          userId (Integer): Foreign key referencing user
 *                          bookId (Integer): Foreign key referencing book
 *                          isValid (Integer): Validity flag (0=invalid, 1=valid)
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Menghui Yao
 *     Reviewer: Menghui Yao
 *     Review Date: 2025/5/8
 *     Modification Date: 2025/5/8
 *     Modification Description: Initial implementation of basket entity
 * </pre>
 */
public class Basket {
    private Integer id;

    private Integer userId;

    private Integer bookId;

    private Integer isValid;

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

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
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
        sb.append(", isValid=").append(isValid);
        sb.append("]");
        return sb.toString();
    }
}