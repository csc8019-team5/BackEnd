/**
 * Class: BasketControllerTest
 * File: BasketControllerTest.java
 * Created on: 2025/05/06
 * Author: Qingyu Cao
 *
 * Description:
 * <pre>
 *     Function: Test cases for the BasketController class, responsible for basket operations like insert, delete, check, pay, and load.
 *     Interface Description:
 *         - Calling Sequence:
 *                          testLoadBasket: Tests retrieving the basket by userId.
 *                          testCheckBookInBasket: Tests checking if a book is already in the basket.
 *                          testInsertBookIntoBasket: Tests inserting a book into the basket.
 *                          testDeleteBookFromBasket: Tests deleting a book from the basket.
 *                          testPayAndRecord: Tests the pay functionality and moving books to USER_BOOK.
 *         - Argument Description:
 *                          userId (Integer): The ID of the user in the test cases.
 *                          bookId (Integer): The ID of the book in the test cases.
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: Qingyu Cao
 *     Reviewer: -
 *     Review Date: -
 *     Modification Date: -
 *     Modification Description: Initial creation
 * </pre>
 */

 package uk.ac.ncl.team5project;

 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.http.MediaType;
 import org.springframework.test.web.servlet.MockMvc;
 import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 import org.springframework.web.context.WebApplicationContext;
 import uk.ac.ncl.team5project.Team5ProjectApplication;
 
 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
 @SpringBootTest(classes = Team5ProjectApplication.class)
 public class BasketControllerTest {
 
     @Autowired
     private WebApplicationContext webApplicationContext;
 
     private MockMvc mockMvc;
 
     private final String BASE_URL = "/api/v1/basket";
 
     @BeforeEach
     public void setup() {
         mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
     }
 
     @Test
     public void testLoadBasket() throws Exception {
         mockMvc.perform(get(BASE_URL + "/loadBasket")
                         .param("userId", "1")
                         .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
     }
 
     @Test
     public void testCheckBookInBasket() throws Exception {
         mockMvc.perform(get(BASE_URL + "/check")
                         .param("userId", "1")
                         .param("bookId", "2")
                         .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
     }
 
     @Test
     public void testInsertBookIntoBasket() throws Exception {
         mockMvc.perform(post(BASE_URL + "/insert")
                         .param("userId", "1")
                         .param("bookId", "3")
                         .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
     }
 
     @Test
     public void testDeleteBookFromBasket() throws Exception {
         mockMvc.perform(patch(BASE_URL + "/delete")
                         .param("userId", "1")
                         .param("bookId", "3")
                         .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
     }
 
     @Test
     public void testPayAndRecord() throws Exception {
         // This assumes loadBasket has already been called before this, otherwise storedUserId may be null
         mockMvc.perform(get(BASE_URL + "/pay")
                         .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
     }
 }
 