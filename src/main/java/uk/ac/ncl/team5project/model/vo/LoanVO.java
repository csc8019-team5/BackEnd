package uk.ac.ncl.team5project.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uk.ac.ncl.team5project.entity.jpa.Loan;
import uk.ac.ncl.team5project.entity.jpa.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Class: LoanVO
 * File: LoanVO.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Value Object class for Loan entity, designed to transfer loan data between
 *               the service layer and controller layer for API responses. Manages the
 *               presentation format of loan records including associated book information
 *               and properly formatted dates.
 *     Interface Description:
 *         - toLoanVO(Loan): Static factory method that converts a Loan entity to LoanVO.
 *         - toLoanVO(Loan, Book): Static factory method that converts a Loan entity with
 *                                associated Book entity to LoanVO.
 *     Argument Description:
 *         - loanId: Unique identifier for the loan record.
 *         - book: Associated book information in BookVO format.
 *         - userId: ID of the user who borrowed the book.
 *         - bookId: ID of the borrowed book.
 *         - borrowDate: Date when the book was borrowed.
 *         - dueDate: Date when the book is due to be returned.
 *         - returnDate: Date when the book was actually returned, null if not yet returned.
 *         - status: Current status of the loan (active, expired, returned).
 *         - message: Optional message associated with the loan record.
 *     List of Subordinate Classes: Book, BookVO.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Initial implementation with support for Loan entity conversion
 *                             and associated Book information.
 * </pre>
 */
@Data
public class LoanVO {
    @JsonProperty("loan_id")
    private Integer loanId;

    private BookVO book;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("book_id")
    private Integer bookId;

    @JsonProperty("borrow_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime borrowDate;

    @JsonProperty("due_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dueDate;

    @JsonProperty("return_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime returnDate;

    private String status;

    private String message;

    /**
     * Convert LocalDate to LocalDateTime (using the start of the day)
     *
     * This utility method converts a LocalDate object to a LocalDateTime object
     * by setting the time component to the beginning of the day (00:00:00).
     * This is necessary because the API requires ISO-8601 formatted timestamps
     * with time components, while the database stores only dates for loan records.
     *
     * The method handles null values gracefully, which is important for fields
     * like returnDate that may be null for active loans.
     *
     * @param date The LocalDate to convert, can be null
     * @return A LocalDateTime representing the start of the specified date (midnight),
     *         or null if the input date is null
     * @throws RuntimeException If there is an error during the date conversion process
     */
    private static LocalDateTime toLocalDateTime(LocalDate date) {
        if (date == null) return null;
        try {
            return LocalDateTime.of(date, LocalTime.of(0, 0, 0));
        } catch (Exception e) {
            throw new RuntimeException("Date conversion failed: " + e.getMessage());
        }
    }

    /**
     * Convert Loan entity to LoanVO
     *
     * This method transforms a database Loan entity into a LoanVO (Value Object)
     * suitable for API responses. The conversion process includes:
     *
     * 1. Basic property mapping from entity to VO fields
     * 2. Date format conversion from LocalDate to ISO-8601 formatted LocalDateTime
     * 3. Status calculation based on return date and due date
     *    - "returned": The loan has a non-null return date
     *    - "expired": The loan is past due but not returned
     *    - "active": The loan is ongoing and not past due
     *
     * This separation of concerns allows the API to present loan data in a
     * consistent format regardless of how it's stored in the database.
     *
     * @param loan The Loan entity to convert from the database layer
     * @return A fully populated LoanVO with calculated status and formatted dates
     * @throws RuntimeException If the loan is null or if any error occurs during conversion
     */
    public static LoanVO toLoanVO(Loan loan) {
        if (loan == null) {
            throw new RuntimeException("Loan record cannot be null");
        }

        try {
            LoanVO vo = new LoanVO();
            vo.setLoanId(loan.getId());
            vo.setUserId(loan.getUserId());
            vo.setBookId(loan.getBookId());


            vo.setBorrowDate(toLocalDateTime(loan.getBorrowDate()));
            vo.setDueDate(toLocalDateTime(loan.getReturnDateEstimated()));
            vo.setReturnDate(toLocalDateTime(loan.getReturnDate()));


            if (loan.getReturnDate() != null) {
                vo.setStatus("returned");
            } else if (loan.getReturnDateEstimated() != null &&
                    loan.getReturnDateEstimated().isBefore(LocalDate.now())) {
                vo.setStatus("expired");
            } else {
                vo.setStatus("active");
            }

            return vo;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert loan record: " + e.getMessage());
        }
    }

    /**
     * Convert Loan entity to LoanVO and set associated Book information
     *
     * This enhanced converter method extends the basic toLoanVO functionality
     * by also incorporating detailed book information. It combines loan details
     * with the associated book data to provide a comprehensive view of what
     * book is involved in the loan transaction.
     *
     * The method performs the following steps:
     * 1. Calls toLoanVO(loan) to convert basic loan information
     * 2. Converts the Book entity to BookVO using BookVO.toBookVO()
     * 3. Sets the book property in the LoanVO with the converted BookVO
     *
     * This approach provides a complete representation of the loan for API
     * responses, particularly useful for endpoints that display loan history
     * or current loans with full book details.
     *
     * @param loan The Loan entity containing borrowing details
     * @param book The associated Book entity with detailed book information
     * @return A LoanVO with both loan details and associated book information
     * @throws RuntimeException If either conversion fails or if any required entity is null
     * @see #toLoanVO(Loan) The base conversion method for loan-only information
     * @see BookVO#toBookVO(Book) The conversion method for book information
     */
    public static LoanVO toLoanVO(Loan loan, Book book) {
        try {
            LoanVO vo = toLoanVO(loan);
            if (vo != null && book != null) {
                BookVO bookVO = BookVO.toBookVO(book);
                if (bookVO == null) {
                    throw new RuntimeException("Failed to convert book information");
                }
                vo.setBook(bookVO);
            }
            return vo;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert loan record and book information: " + e.getMessage());
        }
    }
} 