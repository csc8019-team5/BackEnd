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
        String email = "test" + System.currentTimeMillis() + "@example.com";

        String json = """
        {
            "username": "wensi",
            "email": "%s",
            "password": "12345678"
        }
    """.formatted(email);

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
    //If the test fails due to an expired token, you can log in with 111@11.com and password 123 to obtain a new token.
    @Test
    public void testGetUserInfo() throws Exception {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTFAMTEuY29tIiwicm9sZSI6InVzZXIiLCJpYXQiOjE3NDY3MzYyOTIsImV4cCI6MTc0NjgyMjY5Mn0.UKNTgtM_AhwuRurDKZBLMy7dlncfmU8cUqXgp0KUnwPY48FzoD6otd4Os8P8elfvPD9zpTqLdNZHR6F1wlTipA";

        mockMvc.perform(get("/v1/users/me")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.userId").exists());
    }
    //If the test fails due to an expired token, you can log in with 111@11.com and password 123 to obtain a new token.
    @Test
    public void testUpdateUserInfo() throws Exception {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTFAMTEuY29tIiwicm9sZSI6InVzZXIiLCJpYXQiOjE3NDY3MzYyOTIsImV4cCI6MTc0NjgyMjY5Mn0.UKNTgtM_AhwuRurDKZBLMy7dlncfmU8cUqXgp0KUnwPY48FzoD6otd4Os8P8elfvPD9zpTqLdNZHR6F1wlTipA";
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

