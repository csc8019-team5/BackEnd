package uk.ac.ncl.team5project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uk.ac.ncl.team5project.entity.jpa.Book;
import uk.ac.ncl.team5project.entity.jpa.Loan;
import uk.ac.ncl.team5project.entity.User;
import uk.ac.ncl.team5project.model.vo.BookVO;
import uk.ac.ncl.team5project.model.vo.LoanVO;
import uk.ac.ncl.team5project.repository.BookRepository;
import uk.ac.ncl.team5project.service.LoanService;
import uk.ac.ncl.team5project.service.UserService;
import uk.ac.ncl.team5project.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ncl.team5project.model.vo.UserInfoVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
    private static final int MAX_LOANS = 10; 

    @Autowired
    private LoanService loanService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private BookRepository bookRepository;

    /**
     * Creates a new loan record for borrowing a book.
     * 
     * @param request Request body containing book_id to borrow
     * @param authentication JWT authentication containing user information
     * @return The created Loan object with borrowing details
     * @throws RuntimeException If the book is not available or doesn't exist
     */
    @PostMapping
    public ResponseEntity<?> borrowBook(@RequestBody Map<String, Integer> request, Authentication authentication) {
        try {
            String username = (String) authentication.getPrincipal();
            logger.info("User {} attempting to borrow a book", username);

            Result<?> userResult = userService.getInfo();
            if (userResult.getCode() != 200) {
                logger.error("Failed to get user info for {}: {}", username, userResult.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Result.error(401, "Failed to retrieve user information"));
            }
            
            UserInfoVO userInfo = (UserInfoVO) userResult.getData();
            if (userInfo == null) {
                logger.error("User data is null for {}", username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Result.error(401, "User information does not exist"));
            }
            
            Integer bookId = request.get("book_id");
            if (bookId == null) {
                logger.error("Book ID is missing in the request");
                return ResponseEntity.badRequest()
                        .body(Result.error(400, "Please provide book_id"));
            }

            int duration = 14; // Default 14 days
            Loan loan = loanService.borrowBook(userInfo.getUserId(), bookId, duration);
            logger.info("User {} successfully borrowed book {}", username, bookId);
            
           
            Optional<Book> bookOpt = bookRepository.findById(bookId);
            Book book = bookOpt.orElse(null);
            
            // convert to LoanVO, include book information
            LoanVO loanVO = book != null ? 
                    LoanVO.toLoanVO(loan, book) : 
                    LoanVO.toLoanVO(loan);
                    
            loanVO.setMessage("User " + userInfo.getUserId() + " successfully borrowed book " + bookId);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(loanVO);
        } catch (Exception e) {
            logger.error("Error in borrowBook: {}", e.getMessage());
            if (e.getMessage() != null && e.getMessage().contains("not available")) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Result.conflict(e.getMessage()));
            } else if (e.getMessage() != null && (e.getMessage().contains("loan limit") || e.getMessage().contains("maximum"))) {
                return ResponseEntity.badRequest()
                        .body(Result.error(400, "Maximum loan limit reached"));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Result.error(409, "Failed to borrow book: " + e.getMessage()));
            }
        }
    }

    /**
     * Retrieves all current active loans for the authenticated user.
     * 
     * @param authentication JWT authentication containing user information
     * @return List of active Loan objects
     */
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentLoans(Authentication authentication) {
        try {
            String username = (String) authentication.getPrincipal();
            Result<?> userResult = userService.getInfo();
            if (userResult.getCode() != 200) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Result.error(401, "Failed to retrieve user information"));
            }
            
            UserInfoVO userInfo = (UserInfoVO) userResult.getData();
            if (userInfo == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Result.error(401, "User information does not exist"));
            }
            
            List<Loan> loans = loanService.getCurrentLoans(userInfo.getUserId());
            
            // Convert to LoanVO and add book information
            List<LoanVO> loanVOs = loans.stream()
                    .map(loan -> {
                        // Get book information
                        Optional<Book> bookOpt = bookRepository.findById(loan.getBookId());
                        if (bookOpt.isPresent()) {
                            return LoanVO.toLoanVO(loan, bookOpt.get());
                        } else {
                            return LoanVO.toLoanVO(loan);
                        }
                    })
                    .collect(Collectors.toList());
            
            // Package response according to API documentation format
            Map<String, Object> response = new HashMap<>();
            response.put("loans", loanVOs);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting current loans: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.error(500, "Failed to retrieve current loan information"));
        }
    }

    /**
     * Processes returning a borrowed book based on loan ID.
     * 
     * @param loanId ID of the loan record to be returned
     * @return Updated Loan object with return information
     * @throws RuntimeException If the loan record doesn't exist
     */
    @PutMapping("/{loan_id}/return")
    public ResponseEntity<?> returnBook(@PathVariable("loan_id") Integer loanId) {
        try {
            Loan loan = loanService.returnBook(loanId);
            
            
            Map<String, Object> response = new HashMap<>();
            response.put("loan_id", loan.getId());
            response.put("status", "returned");
            response.put("return_date", loan.getReturnDate().toString() + "T00:00:00Z");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error returning book: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.error(500, "Return book failed: " + e.getMessage()));
        }
    }

    /**
     * Retrieves loan history for the authenticated user.
     * 
     * @param authentication JWT authentication containing user information
     * @return List of all Loan objects for the user
     */
    @GetMapping("/history")
    public ResponseEntity<?> getLoanHistory(Authentication authentication) {
        try {
            String username = (String) authentication.getPrincipal();
            Result<?> userResult = userService.getInfo();
            if (userResult.getCode() != 200) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Result.error(401, "Failed to retrieve user information"));
            }
            
            UserInfoVO userInfo = (UserInfoVO) userResult.getData();
            if (userInfo == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Result.error(401, "User information does not exist"));
            }
            
            List<Loan> loans = loanService.getLoanHistory(userInfo.getUserId());
            
            // Convert to LoanVO and add book information
            List<LoanVO> loanVOs = loans.stream()
                    .map(loan -> {
                        // Get book information
                        Optional<Book> bookOpt = bookRepository.findById(loan.getBookId());
                        if (bookOpt.isPresent()) {
                            return LoanVO.toLoanVO(loan, bookOpt.get());
                        } else {
                            return LoanVO.toLoanVO(loan);
                        }
                    })
                    .collect(Collectors.toList());
            
           
            Map<String, Object> response = new HashMap<>();
            response.put("history", loanVOs);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting loan history: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.error(500, "Failed to retrieve loan history"));
        }
    }
}
