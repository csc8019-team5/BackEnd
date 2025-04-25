package uk.ac.ncl.team5project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uk.ac.ncl.team5project.entity.Loan;
import uk.ac.ncl.team5project.service.LoanService;

import java.util.List;
import java.util.Map;

/**
 * Class: LoanController
 * File: LoanController.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Handles all borrowing operations for the library system, including borrowing books,
 *               querying current loans, returning books, and querying loan history through RESTful APIs.
 *               All operations are protected by JWT authentication to ensure user identity security.
 *     Interface Description:
 *         - borrowBook: Creates a new loan record based on book_id in the request.
 *         - getCurrentLoans: Retrieves all active loans for the authenticated user.
 *         - returnBook: Processes book return based on the loan ID.
 *         - getLoanHistory: Retrieves loan history for the authenticated user.
 *     Calling Sequence:
 *         1. Borrow Book (POST /v1/loans):
 *            Request: POST /v1/loans with Authorization header and {"book_id": 1} in body
 *            Response: Loan object with details
 *         2. Current Loans (GET /v1/loans/current):
 *            Request: GET /v1/loans/current with Authorization header
 *            Response: List of active Loan objects
 *         3. Return Book (PUT /v1/loans/{loan_id}/return):
 *            Request: PUT /v1/loans/5/return with Authorization header
 *            Response: Updated Loan object with return details
 *         4. Loan History (GET /v1/loans/history):
 *            Request: GET /v1/loans/history with Authorization header
 *            Response: List of all Loan objects for the user
 *     Argument Description:
 *         - request: Request body containing book_id for borrowing.
 *         - authentication: JWT authentication object containing user information.
 *         - loanId: Path parameter identifying the loan record to return.
 *         - userId: Extracted from JWT authentication token.
 *         - bookId: Book identifier extracted from request body.
 *         - duration: Loan duration in days (default: 14).
 *     List of Subordinate Classes: Loan, LoanService.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 24/04/2025
 *     Modification Date: 24/04/2025
 *     Modification Description: Initial implementation with JWT authentication integration.
 * </pre>
 */
@RestController
@RequestMapping("/v1/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    /**
     * Creates a new loan record for borrowing a book.
     * 
     * @param request Request body containing book_id to borrow
     * @param authentication JWT authentication containing user information
     * @return The created Loan object with borrowing details
     * @throws RuntimeException If the book is not available or doesn't exist
     */
    @PostMapping
    public Loan borrowBook(@RequestBody Map<String, Integer> request, Authentication authentication) {
        // Extract user ID from JWT authentication
        Integer userId = (Integer) authentication.getPrincipal();
        Integer bookId = request.get("book_id");
        int duration = 14; // Default 14 days
        return loanService.borrowBook(userId, bookId, duration);
    }

    /**
     * Retrieves all current active loans for the authenticated user.
     * 
     * @param authentication JWT authentication containing user information
     * @return List of active Loan objects
     */
    @GetMapping("/current")
    public List<Loan> getCurrentLoans(Authentication authentication) {
        // Extract user ID from JWT authentication
        Integer userId = (Integer) authentication.getPrincipal();
        return loanService.getCurrentLoans(userId);
    }

    /**
     * Processes returning a borrowed book based on loan ID.
     * 
     * @param loanId ID of the loan record to be returned
     * @return Updated Loan object with return information
     * @throws RuntimeException If the loan record doesn't exist
     */
    @PutMapping("/{loan_id}/return")
    public Loan returnBook(@PathVariable("loan_id") Integer loanId) {
        return loanService.returnBook(loanId);
    }

    /**
     * Retrieves loan history for the authenticated user.
     * 
     * @param authentication JWT authentication containing user information
     * @return List of all Loan objects for the user
     */
    @GetMapping("/history")
    public List<Loan> getLoanHistory(Authentication authentication) {
        // Extract user ID from JWT authentication
        Integer userId = (Integer) authentication.getPrincipal();
        return loanService.getLoanHistory(userId);
    }
}
