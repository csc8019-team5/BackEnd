/**
 * Class: ReviewControllerTest
 * File: ReviewControllerTest.java
 * Created on: 2025/05/24
 * Author: Menghui Yao
 *
 * Description:
 * <pre>
 *     Function: Test cases for the ReviewController class, which is responsible for managing review-related operations.
 *               These test cases verify the functionality of endpoints for viewing, creating, updating, and deleting reviews.
 *     Interface Description:
 *         - Calling Sequence:
 *                          testSelectReviewByBookId: Tests the endpoint to fetch all reviews associated with a book by its ID.
 *                          testSelectReviewByUserId: Tests the endpoint to fetch all reviews associated with a user by their ID.
 *                          testUpdateReviewByBookIdUserId: Tests the endpoint to create a new review or update an existing review for a specific book by a specific user.
 *                          testDeleteReviewByBookIdUserId: Tests the endpoint to delete a review for a specific book by a specific user.
 *         - Argument Description:
 *                          id (Integer): Book or User ID used for fetching or managing reviews in the test cases.
 *                          bookId (Integer): The ID of the book in the test cases.
 *                          userId (Integer): The ID of the user in the test cases.
 *                          content (String): The content of the review in the test cases.
 *                          pageNum (Integer): The page number for pagination in the test cases (optional, defaults to 1).
 *                          pageSize (Integer): The number of reviews per page in the test cases (optional, defaults to 3).
 *         - List of Subordinate Classes:
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Menghui Yao
 *     Reviewer: Menghui Yao
 *     Review Date: 2025/05/04
 *     Modification Date: 2025/05/04
 *     Modification Description: none
 * </pre>
 */
package uk.ac.ncl.team5project;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uk.ac.ncl.team5project.Team5ProjectApplication;
import uk.ac.ncl.team5project.config.Result;
import uk.ac.ncl.team5project.entity.Review;
import uk.ac.ncl.team5project.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Team5ProjectApplication.class)
public class ReviewControllerTest {

    /**
     * The WebApplicationContext instance, which is used to build the MockMvc object.
     */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * The MockMvc instance, which is used to simulate HTTP requests for testing.
     */
    private MockMvc mockMvc;

    /**
     * Set up the test environment before each test method.
     * This method initializes the MockMvc object using the WebApplicationContext.
     */
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Test the selectReviewByBookId method in the ReviewController.
     * This method simulates a GET request to the /v1/reviews/selectReviewByBookId endpoint
     * and verifies that the response status code is OK.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testSelectReviewByBookId() throws Exception {
        // Simulate a request and verify the status code
        mockMvc.perform(get("/v1/reviews/selectReviewByBookId")
                        .param("id", "1")
                        .param("pageNum", "1")
                        .param("pageSize", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test the selectReviewByUserId method in the ReviewController.
     * This method simulates a GET request to the /v1/reviews/selectReviewByUserId endpoint
     * and verifies that the response status code is OK.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testSelectReviewByUserId() throws Exception {
        mockMvc.perform(get("/v1/reviews/selectReviewByUserId")
                        .param("id", "1")
                        .param("pageNum", "1")
                        .param("pageSize", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test the updateReviewByBookIdUserId method in the ReviewController.
     * This method simulates a POST request to the /v1/reviews/updateReviewByBookIdUserId endpoint
     * and verifies that the response status code is OK.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testUpdateReviewByBookIdUserId() throws Exception {
        String content = "This is a test review content";
        mockMvc.perform(post("/v1/reviews/updateReviewByBookIdUserId")
                        .param("bookId", "1")
                        .param("userId", "1")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Test the deleteReviewByBookIdUserId method in the ReviewController.
     * This method simulates a DELETE request to the /v1/reviews/deleteReviewByBookIdUserId endpoint
     * and verifies that the response status code is OK.
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    public void testDeleteReviewByBookIdUserId() throws Exception {
        mockMvc.perform(delete("/v1/reviews/deleteReviewByBookIdUserId")
                        .param("bookId", "1")
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}    