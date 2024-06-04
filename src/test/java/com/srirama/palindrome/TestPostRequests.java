package com.srirama.palindrome;

import com.srirama.palindrome.model.PalindromeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestPostRequests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenValidInput_thenReturns200() throws Exception {
        PalindromeRequest request = new PalindromeRequest();
        request.setUsername("test_user");
        request.setValue("madam");

        mockMvc.perform(post("/v1/palindrome/check_palindrome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"test_user\", \"value\": \"madam\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("test_user"))
                .andExpect(jsonPath("$.value").value("madam"))
                .andExpect(jsonPath("$.palindrome").value(true));
    }

    @Test
    public void whenInvalidInput_thenReturns500() throws Exception {
        mockMvc.perform(post("/v1/palindrome/check_palindrome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"test_user\", \"value\": \"madam 123\"}"))
                .andExpect(status().isInternalServerError());
    }
}