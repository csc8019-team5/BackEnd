package uk.ac.ncl.team5project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import uk.ac.ncl.team5project.entity.jpa.Book;
import uk.ac.ncl.team5project.entity.jpa.Loan;
import uk.ac.ncl.team5project.model.vo.UserInfoVO;
import uk.ac.ncl.team5project.repository.BookRepository;
import uk.ac.ncl.team5project.service.LoanService;
import uk.ac.ncl.team5project.service.UserService;
import uk.ac.ncl.team5project.util.Result;
import uk.ac.ncl.team5project.controller.LoanController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class: LoanControllerTest
 * File: LoanControllerTest.java
 * Created on: 24/04/2025
 * Author: Yixin Zhang
 *
 * Description:
 * <pre>
 *     Function: Test the RESTful API endpoints in LoanController, verify the functionality of book borrowing
 *               and returning systems. Ensures the correctness of loan operations through simulated HTTP requests.
 *     Interface Description:
 *         - testBorrowBook: Test the book borrowing interface, verifying successful loan creation.
 *         - testReturnBook: Test the book returning interface, verifying proper status updates.
 *         - testGetCurrentLoans: Test the interface for retrieving a user's current active loans.
 *         - testGetLoanHistory: Test the interface for retrieving a user's complete loan history.
 *     Calling Sequence:
 *         - Run test class or individual test methods via JUnit.
 *         - Use MockMvc to simulate HTTP requests and verify responses.
 *     Argument Description:
 *         - mockMvc: Spring MVC test tool, used to simulate HTTP requests.
 *         - loanService: Mocked LoanService, injected into the controller.
 *         - userService: Mocked UserService, provides user authentication information.
 *         - bookRepository: Mocked BookRepository, provides book information.
 *         - authentication: Mocked Authentication object to simulate authenticated user.
 *         - loanController: Controller instance being tested.
 *     List of Subordinate Classes: MockMvc, LoanService, UserService, BookRepository, Authentication, LoanController.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Yixin Zhang
 *     Reviewer: Yixin Zhang
 *     Review Date: 06/05/2025
 *     Modification Date: 06/05/2025
 *     Modification Description: Implemented unit tests for loan-related APIs to verify borrowing,
 *     returning, and querying functionalities with proper authentication.
 * </pre>
 */
@ExtendWith(MockitoExtension.class)
public class LoanControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LoanService loanService;

    @Mock
    private UserService userService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private LoanController loanController;

    @BeforeEach
    public void setup() {
        // Mock Authentication to return username
        lenient().when(authentication.getPrincipal()).thenReturn("testUser");

        // Use standaloneSetup instead of webAppContextSetup
        mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();

        // Setup user information mock
        UserInfoVO userInfo = new UserInfoVO();
        userInfo.setUserId(1);
        userInfo.setUserName("testUser");
        Result<UserInfoVO> userResult = new Result<>();
        userResult.setCode(200);
        userResult.setData(userInfo);

        // Use lenient() to allow unused mocks
        lenient().when(userService.getInfo()).thenReturn((Result) userResult);

        // Setup book information mock
        Book book = new Book();
        book.setId(1);
        book.setName("Test Book");
        book.setAuthor("Test Author");
        lenient().when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));
    }

    /**
     * Test the book borrowing endpoint
     * POST /v1/loans
     */
    @Test
    public void testBorrowBook() throws Exception {
        // Mock loan service return result
        Loan loan = new Loan();
        loan.setId(1);
        loan.setUserId(1);
        loan.setBookId(1);
        loan.setBorrowDate(LocalDate.now());
        loan.setReturnDateEstimated(LocalDate.now().plusDays(14));

        when(loanService.borrowBook(anyInt(), anyInt(), anyInt())).thenReturn(loan);

        // Execute borrow request and provide mocked Authentication object
        mockMvc.perform(post("/v1/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"book_id\": 1}")
                        .header("Authorization", "Bearer test-token")
                        .principal(authentication))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.loan_id").value(1))
                .andExpect(jsonPath("$.book_id").value(1));
    }

    /**
     * Test the book returning endpoint
     * PUT /v1/loans/{loan_id}/return
     */
    @Test
    public void testReturnBook() throws Exception {
        // Mock return service result
        Loan loan = new Loan();
        loan.setId(1);
        loan.setUserId(1);
        loan.setBookId(1);
        loan.setBorrowDate(LocalDate.now().minusDays(5));
        loan.setReturnDateEstimated(LocalDate.now().plusDays(9));
        loan.setReturnDate(LocalDate.now());

        when(loanService.returnBook(anyInt())).thenReturn(loan);

        // Execute return request
        mockMvc.perform(put("/v1/loans/1/return")
                        .header("Authorization", "Bearer test-token")
                        .principal(authentication))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loan_id").value(1))
                .andExpect(jsonPath("$.status").value("returned"));
    }

    /**
     * Test the current loans query endpoint
     * GET /v1/loans/current
     */
    @Test
    public void testGetCurrentLoans() throws Exception {
        // Mock current loan list
        List<Loan> loans = new ArrayList<>();
        Loan loan = new Loan();
        loan.setId(1);
        loan.setUserId(1);
        loan.setBookId(1);
        loan.setBorrowDate(LocalDate.now().minusDays(3));
        loan.setReturnDateEstimated(LocalDate.now().plusDays(11));
        loans.add(loan);

        when(loanService.getCurrentLoans(anyInt())).thenReturn(loans);

        // Execute current loans query request
        mockMvc.perform(get("/v1/loans/current")
                        .header("Authorization", "Bearer test-token")
                        .principal(authentication))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loans[0].loan_id").value(1))
                .andExpect(jsonPath("$.loans[0].status").value("active"));
    }

    /**
     * Test the loan history query endpoint
     * GET /v1/loans/history
     */
    @Test
    public void testGetLoanHistory() throws Exception {
        // Mock loan history list
        List<Loan> loans = new ArrayList<>();
        // Returned loan
        Loan returnedLoan = new Loan();
        returnedLoan.setId(1);
        returnedLoan.setUserId(1);
        returnedLoan.setBookId(1);
        returnedLoan.setBorrowDate(LocalDate.now().minusDays(20));
        returnedLoan.setReturnDateEstimated(LocalDate.now().minusDays(6));
        returnedLoan.setReturnDate(LocalDate.now().minusDays(7));
        loans.add(returnedLoan);

        // Current active loan
        Loan activeLoan = new Loan();
        activeLoan.setId(2);
        activeLoan.setUserId(1);
        activeLoan.setBookId(2);
        activeLoan.setBorrowDate(LocalDate.now().minusDays(5));
        activeLoan.setReturnDateEstimated(LocalDate.now().plusDays(9));
        loans.add(activeLoan);

        when(loanService.getLoanHistory(anyInt())).thenReturn(loans);

        // Execute loan history query request
        mockMvc.perform(get("/v1/loans/history")
                        .header("Authorization", "Bearer test-token")
                        .principal(authentication))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.history[0].loan_id").value(1))
                .andExpect(jsonPath("$.history[0].status").value("returned"))
                .andExpect(jsonPath("$.history[1].loan_id").value(2))
                .andExpect(jsonPath("$.history[1].status").value("active"));
    }
}