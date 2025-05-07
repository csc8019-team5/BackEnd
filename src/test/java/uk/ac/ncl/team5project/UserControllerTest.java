package uk.ac.ncl.team5project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * @file UserControllerTest.java
 * @date 2025-05-07
 * @function_description: Integration test class for testing user-related API endpoints.
 * @interface_description:
 * Includes tests for user registration, login, admin login, retrieving and updating user info.
 * Covers normal and error cases using MockMvc to simulate HTTP requests.
 * @calling_sequence: Test → MockMvc → UserController → UserService
 * @arguments_description:
 * - JSON payloads with email, username, password (for registration & login)
 * - Authorization header with JWT token (for user info operations)
 * @list_of_subordinate_classes: UserController, UserService, JWT Utility
 * @discussion:
 * Ensures endpoint correctness and expected behavior including token issuance and user validation.
 * Tokens are manually injected for authenticated endpoint testing.
 * @development_history: Created on 2025-05-07 to support backend testing of user module.
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-05-07
 * @modification_date: 2025-05-07
 * @description: End-to-end test for user controller features including registration, login, and profile management.
 */

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegisterSuccess() throws Exception {
        String json = """
            {
                "username": "wensih",
                "email": "wensih@gmail.com",
                "password": "12345678"
            }
        """;

        mockMvc.perform(post("/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void testLoginSuccess() throws Exception {
        String json = """
            {
                "email": "111@11.com",
                "password": "123"
            }
        """;

        mockMvc.perform(post("/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.data.token").exists());
    }

    @Test
    public void testLoginFailure() throws Exception {
        String json = """
            {
                "email": "wrong@example.com",
                "password": "wrongpass"
            }
        """;

        mockMvc.perform(post("/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("User not found"));
    }

    @Test
    public void testAdminLoginSuccess() throws Exception {
        String json = """
            {
                "email": "admin@gmail.com",
                "password": "admin123"
            }
        """;

        mockMvc.perform(post("/v1/users/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.token").exists());
    }

    @Test
    public void testGetUserInfo() throws Exception {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTFAMTEuY29tIiwicm9sZSI6InVzZXIiLCJpYXQiOjE3NDY2MzU0MzQsImV4cCI6MTc0NjcyMTgzNH0.cuoa04YGofsHUI5IbhzI77-75G4nY7x7foXEeCf-bkCOrOvy6Fy13Bk5oZ1fmLtwd3RV6AsdLFbit2HDNZEVOA";

        mockMvc.perform(get("/v1/users/me")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.userId").exists());
    }

    @Test
    public void testUpdateUserInfo() throws Exception {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTFAMTEuY29tIiwicm9sZSI6InVzZXIiLCJpYXQiOjE3NDY2MzU0MzQsImV4cCI6MTc0NjcyMTgzNH0.cuoa04YGofsHUI5IbhzI77-75G4nY7x7foXEeCf-bkCOrOvy6Fy13Bk5oZ1fmLtwd3RV6AsdLFbit2HDNZEVOA";
        String json = """
            {
                "username": "newusername",
                "password": "newpass"
            }
        """;

        mockMvc.perform(put("/v1/users/me")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }
}


