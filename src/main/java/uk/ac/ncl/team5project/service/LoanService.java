package uk.ac.ncl.team5project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ncl.team5project.entity.jpa.Loan;
import uk.ac.ncl.team5project.entity.jpa.Book;
import uk.ac.ncl.team5project.repository.LoanRepository;
import uk.ac.ncl.team5project.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class: LoanService
 * File: LoanService.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Implements the core business logic of the book lending system, including processing
 *               book borrowing, returning books, querying current loans and loan history, and
 *               automatic processing of expired loans.
 *     Interface Description:
 *         - borrowBook: Creates new loan record and updates book availability status.
 *         - getCurrentLoans: Retrieves active loans for a specific user.
 *         - returnBook: Processes book return and updates availability.
 *         - getLoanHistory: Retrieves all loan records for a specific user.
 *         - getAllLoans: Internal method retrieving all loans for a user.
 *         - autoExpire: Scheduled method that handles expired loans automatically.
 *     Calling Sequence:
 *         - Borrow Book: Loan loan = loanService.borrowBook(1, 2, 14);
 *         - Current Loans: List<Loan> loans = loanService.getCurrentLoans(1);
 *         - Return Book: Loan loan = loanService.returnBook(5);
 *         - Loan History: List<Loan> history = loanService.getLoanHistory(1);
 *         - Auto Expire: loanService.autoExpire(); (called by scheduler)
 *     Argument Description:
 *         - userId: Identifies the borrowing user in the system.
 *         - bookId: Identifies the book being borrowed or returned.
 *         - borrowDurationDays: Number of days the book can be borrowed.
 *         - loanId: Unique identifier for the loan record being processed.
 *     List of Subordinate Classes: Loan, Book, LoanRepository, BookRepository.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Implemented transaction management and added auto-expiry functionality.
 * </pre>
 */
@Service
@Transactional
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);
    private static final int MAX_LOANS = 5; // Maximum number of simultaneous loans

    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private BookRepository bookRepository;

    /**
     * Creates a loan record for borrowing a book.
     * Updates book availability status to unavailable.
     * 
     * @param userId User ID of the borrower
     * @param bookId Book ID to be borrowed
     * @param borrowDurationDays Number of days for borrowing period
     * @return The created Loan record
     * @throws RuntimeException If book doesn't exist or is unavailable
     */
    @Transactional
    public Loan borrowBook(Integer userId, Integer bookId, int borrowDurationDays) {
        logger.info("Attempting to borrow book {} for user {}", bookId, userId);

        // Check the current number of loans for the user
        List<Loan> currentLoans = getCurrentLoans(userId);
        if (currentLoans.size() >= MAX_LOANS) {
            logger.warn("User {} has reached maximum loan limit", userId);
            throw new RuntimeException("Maximum loan limit reached (" + MAX_LOANS + " books)");
        }
        
        // Check if the user has already borrowed this book
        boolean hasBook = currentLoans.stream()
            .anyMatch(loan -> loan.getBookId().equals(bookId));
        if (hasBook) {
            logger.warn("User {} has already borrowed book {}", userId, bookId);
            throw new RuntimeException("You have already borrowed this book");
        }

        // Check if book exists and is available
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> {
                    logger.error("Book {} not found", bookId);
                    return new RuntimeException("Book does not exist");
                });

        // Check if book is available
        if (!Boolean.TRUE.equals(book.getAvailable())) {
            logger.warn("Book {} is not available for borrowing", bookId);
            throw new RuntimeException("The book is currently unavailable for borrowing");
        }

        // Set book status to unavailable
        book.setAvailable(false);
        bookRepository.save(book);
        logger.info("Book {} status updated to unavailable", bookId);

        // Create loan record
        Loan loan = new Loan();
        loan.setUserId(userId);
        loan.setBookId(bookId);
        loan.setBorrowDate(LocalDate.now());
        loan.setReturnDateEstimated(LocalDate.now().plusDays(borrowDurationDays));
        loan.setReturnDate(null);
        
        Loan savedLoan = loanRepository.save(loan);
        logger.info("Loan record created successfully: {}", savedLoan);
        return savedLoan;
    }

    /**
     * Retrieves current active loans for a specific user.
     * 
     * @param userId User ID to get loans for
     * @return List of active loans (not returned)
     */
    public List<Loan> getCurrentLoans(Integer userId) {
        // Get user's current active loans
        return loanRepository.findByUserIdAndReturnDateIsNull(userId);
    }
    
    /**
     * Processes the return of a borrowed book.
     * Updates book availability status to available.
     * 
     * @param loanId Loan record ID to process
     * @return Updated Loan record with return date
     * @throws RuntimeException If loan record doesn't exist
     */
    @Transactional
    public Loan returnBook(Integer loanId) {
        logger.info("Processing return for loan ID: {}", loanId);
        
        if (loanId == null || loanId <= 0) {
            logger.error("Invalid loan ID: {}", loanId);
            throw new RuntimeException("Invalid loan record ID");
        }

        // Find loan record by exact ID
        Loan loan = loanRepository.findLoanById(loanId);
        if (loan == null) {
            logger.error("Loan record not found for ID: {}", loanId);
            throw new RuntimeException("Loan record " + loanId + " does not exist");
        }

        // Validate the integrity of the loan record
        if (loan.getUserId() == null || loan.getBookId() == null || loan.getBorrowDate() == null) {
            logger.error("Invalid loan record data for ID: {}", loanId);
            throw new RuntimeException("Incomplete loan record data");
        }
        
        // If already returned, return directly
        if (loan.getReturnDate() != null) {
            logger.info("Book already returned for loan ID: {}", loanId);
            return loan;
        }
        
        try {
            // Update loan record
            loan.setReturnDate(LocalDate.now());
            Loan updatedLoan = loanRepository.save(loan);
            logger.info("Loan record updated for ID: {}", loanId);

            // Update book status
            Book book = bookRepository.findById(loan.getBookId())
                    .orElseThrow(() -> {
                        logger.error("Book not found for loan ID: {}", loanId);
                        return new RuntimeException("Book does not exist");
                    });
            book.setAvailable(true);
            bookRepository.save(book);
            logger.info("Book status updated to available for book ID: {}", loan.getBookId());
            
            return updatedLoan;
        } catch (Exception e) {
            logger.error("Error occurred while returning the book: {}", e.getMessage());
            throw new RuntimeException("Error occurred while returning the book: " + e.getMessage());
        }
    }
    
    /**
     * Retrieves loan history for a specific user.
     * Returns all loan records regardless of status.
     * 
     * @param userId User ID to get history for
     * @return List of all loan records for the user
     */
    public List<Loan> getLoanHistory(Integer userId) {
        // Get all returned loans
        return loanRepository.findByUserId(userId);
    }
    
    /**
     * Retrieves all loan records for a specific user.
     * Supports the loan history feature.
     * 
     * @param userId User ID to get records for
     * @return Complete list of loan records
     */
    public List<Loan> getAllLoans(Integer userId) {
        // Get all loan records including current and historical
        return loanRepository.findByUserId(userId);
    }
    
    /**
     * Automatically processes expired loans.
     * Sets return date for expired loans and updates book availability.
     * Invoked by scheduled task.
     */
    @Transactional
    public void autoExpire() {
        List<Loan> activeLoans = loanRepository.findByReturnDateIsNull();
        LocalDate today = LocalDate.now();

        for (Loan loan : activeLoans) {
            if (loan.getReturnDateEstimated() != null && loan.getReturnDateEstimated().isBefore(today)) {

                loan.setReturnDate(loan.getReturnDateEstimated()); //Set return date to the estimated due date
                loanRepository.save(loan);
                

                Book book = bookRepository.findById(loan.getBookId())
                        .orElseThrow(() -> new RuntimeException("Book not found"));
                // Set book status to available (true) because it's considered returned (even if overdue) by the system
                book.setAvailable(true);
                bookRepository.save(book);
            }
        }
    }
}
