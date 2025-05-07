package uk.ac.ncl.team5project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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

    private final String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTFAMTEuY29tIiwicm9sZSI6InVzZXIiLCJpYXQiOjE3NDY2MzU0MzQsImV4cCI6MTc0NjcyMTgzNH0.cuoa04YGofsHUI5IbhzI77-75G4nY7x7foXEeCf-bkCOrOvy6Fy13Bk5oZ1fmLtwd3RV6AsdLFbit2HDNZEVOA";

    @Test
    public void testAddToWishlist() throws Exception {
        WishlistAddDTO dto = new WishlistAddDTO();
        dto.setBook_id(1); // 请确认 book_id=1 存在

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
        Integer wishlistIdToDelete = 1; // 请确保该 ID 存在且属于当前用户

        mockMvc.perform(delete("/v1/wishlist/{wishlist_id}", wishlistIdToDelete)
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
