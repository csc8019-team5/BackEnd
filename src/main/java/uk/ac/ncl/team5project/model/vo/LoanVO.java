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
     * @param date The LocalDate to convert
     * @return A LocalDateTime representing the start of the specified date, or null if input is null
     * @throws RuntimeException If the date conversion fails
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
     * @param loan The Loan entity to convert
     * @return The converted LoanVO with basic loan information
     * @throws RuntimeException If the loan record is null or conversion fails
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
     * @param loan The Loan entity to convert
     * @param book The associated Book entity
     * @return The converted LoanVO with loan information and associated book details
     * @throws RuntimeException If the conversion of either loan or book fails
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