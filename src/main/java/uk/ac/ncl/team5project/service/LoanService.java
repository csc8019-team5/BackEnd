package uk.ac.ncl.team5project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.ncl.team5project.entity.Loan;
import uk.ac.ncl.team5project.entity.Book;
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
        // Check if book exists and is available
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
                
        if (book.getAvailable() == null || book.getAvailable() != 1) {
            throw new RuntimeException("The book is not available for borrowing");
        }
        
        // Set book status to unavailable
        book.setAvailable(0);
        bookRepository.save(book);
        
        // Create loan record
        Loan loan = new Loan();
        loan.setUserId(userId);
        loan.setBookId(bookId);
        loan.setBorrowDate(LocalDate.now());
        loan.setReturnDateEstimated(LocalDate.now().plusDays(borrowDurationDays));
        loan.setReturnDate(null);
        return loanRepository.save(loan);
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
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan record not found"));
        
        // If already returned, return directly
        if (loan.getReturnDate() != null) {
            return loan;
        }
        
        // Update loan record
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);

        // Set book status to available
        Book book = bookRepository.findById(loan.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setAvailable(1);
        bookRepository.save(book);
        
        return loan;
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
                book.setAvailable(1);
                bookRepository.save(book);
            }
        }
    }
}
