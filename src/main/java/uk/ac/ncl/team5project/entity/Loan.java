package uk.ac.ncl.team5project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Class: Loan
 * File: Loan.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Represents a loan record in the library system, tracking the borrowing relationship
 *               between users and books, including loan status and time information.
 *     Interface Description:
 *         - id (Integer): The unique identifier of the loan record.
 *         - userId (Integer): The ID of the user who borrowed the book.
 *         - bookId (Integer): The ID of the borrowed book.
 *         - borrowDate (LocalDate): The date when the book was borrowed.
 *         - returnDateEstimated (LocalDate): The expected return date.
 *         - returnDate (LocalDate): The actual return date (null if not returned).
 *         - getStatus(): Virtual method that returns the loan status based on dates.
 *         - getDueDate(): Virtual method that returns the due date (alias for returnDateEstimated).
 *     Calling Sequence:
 *         - Created by LoanService during borrowBook operation.
 *         - Updated by LoanService during returnBook operation.
 *         - Retrieved for display in getCurrentLoans and getLoanHistory.
 *     Argument Description:
 *         - id: Unique identifier assigned by the database.
 *         - userId: Identifies the borrowing user.
 *         - bookId: Identifies the borrowed book.
 *         - borrowDate: The date when the loan was created.
 *         - returnDateEstimated: Calculated as borrowDate + duration.
 *         - returnDate: Set to the current date when the book is returned.
 *     List of Subordinate Classes: None.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Initial implementation with virtual status methods.
 * </pre>
 */
@Data
@Entity
@Table(name = "\"USER_BOOK\"") 
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    @Column(name = "return_date_estimated")
    private LocalDate returnDateEstimated;

    @Column(name = "return_date")
    private LocalDate returnDate;

    /**
     * Gets the current status of this loan.
     * Calculates status based on return date and estimated return date.
     * 
     * @return "returned" if returned, "expired" if past due date, "active" otherwise
     */
    @Transient
    public String getStatus() {
        if (returnDate != null) return "returned";
        if (returnDateEstimated != null && returnDateEstimated.isBefore(LocalDate.now())) return "expired";
        return "active";
    }

    /**
     * Gets the due date for this loan.
     * 
     * @return The estimated return date
     */
    @Transient
    public LocalDate getDueDate() {
        return returnDateEstimated;
    }

    /**
     * Virtual setter for status field.
     * Not used for actual data modification.
     * 
     * @param status Status value (not stored)
     */
    @Transient
    public void setStatus(String status) {
        // Virtual field, no implementation
    }

}
