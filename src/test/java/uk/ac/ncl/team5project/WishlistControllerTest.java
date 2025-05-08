package uk.ac.ncl.team5project;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.ncl.team5project.entity.Wishlist;
import uk.ac.ncl.team5project.mapper.WishlistMapper;
import uk.ac.ncl.team5project.model.dto.WishlistAddDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



/**
 * @file WishlistControllerTest.java
 * @date 2025-05-07
 * @function_description: Integration tests for wishlist controller endpoints.
 * @interface_description: Covers add, retrieve (with pagination), delete, and stat API calls.
 * @calling_sequence: Test → MockMvc → WishlistController → WishlistService
 * @arguments_description: Uses JSON DTOs and JWT token to test secured endpoints.
 * @list_of_subordinate_classes: WishlistAddDTO, WishlistController
 * @discussion: Relies on JWT token to resolve current user; token must be valid and matched to existing user.
 * @development_history: Created on 2025-05-07 to validate wishlist endpoints.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-05-07
 * @modification_date: 2025-05-07
 * @description: Tests wishlist feature end-to-end including addition, deletion, querying and stats.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WishlistMapper wishlistMapper;
    //If the test fails due to an expired token, you can log in with 111@11.com and password 123 to obtain a new token.
    private final String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTFAMTEuY29tIiwicm9sZSI6InVzZXIiLCJpYXQiOjE3NDY3MzYyOTIsImV4cCI6MTc0NjgyMjY5Mn0.UKNTgtM_AhwuRurDKZBLMy7dlncfmU8cUqXgp0KUnwPY48FzoD6otd4Os8P8elfvPD9zpTqLdNZHR6F1wlTipA";

    @BeforeEach
    public void clearWishlist() {
        wishlistMapper.delete(
                new QueryWrapper<Wishlist>()
                        .eq("user_id", 6)
                        .eq("book_id", 2)  
        );
    }

    @Test
    public void testAddToWishlist() throws Exception {
        WishlistAddDTO dto = new WishlistAddDTO();
        dto.setBook_id(2); 

        mockMvc.perform(post("/v1/wishlist")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void testGetWishlist() throws Exception {
        mockMvc.perform(get("/v1/wishlist")
                        .header("Authorization", token)
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void testDeleteFromWishlist() throws Exception {
        // Add a record with book_id=10
        WishlistAddDTO dto = new WishlistAddDTO();
        dto.setBook_id(10); 

        mockMvc.perform(post("/v1/wishlist")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));

        // Find out the wishlist_id that was just inserted (user_id=6 is the user corresponding to the token)
        Integer wishlistId = wishlistMapper.selectOne(
                new QueryWrapper<Wishlist>()
                        .eq("user_id", 6)
                        .eq("book_id", 10)
        ).getId();

        // Delete the record that was just inserted
        mockMvc.perform(delete("/v1/wishlist/{wishlist_id}", wishlistId)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }


    @Test
    public void testGetStat() throws Exception {
        mockMvc.perform(get("/v1/wishlist/stat")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }
}
