package com.srirama.palindrome;

import com.srirama.palindrome.controller.PalindromeController;
import com.srirama.palindrome.data.AsyncDataStoreService;
import com.srirama.palindrome.service.CacheService;
import com.srirama.palindrome.service.PalindromeService;
import com.srirama.palindrome.validation.NoSpaceAndNumberValidator;
import com.srirama.palindrome.validation.ValidationException;
import com.srirama.palindrome.validation.ValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class PalindromeControllerTests {
    @Mock
    AsyncDataStoreService service;
    @Mock
    PalindromeService palindromeService;
    @Mock
    CacheService cacheService;
    ValidationService validationService;
    PalindromeController controller;

    @BeforeEach
    public void setup() {
        validationService = new ValidationService(List.of(new NoSpaceAndNumberValidator()));
        controller = new PalindromeController(service, palindromeService, cacheService, validationService);
    }

    @Test
    void checkPolindromeTest() throws IOException {
        String string = "aba";

        Mockito.when(palindromeService.isPalindrome(string)).thenReturn(true);
        boolean checkPalindrome = controller.checkPalindrome("user", string);
        Assertions.assertTrue(checkPalindrome);

    }

    @Test
    void checkNotPolindromeTest() throws IOException {
        String string = "abaa";

        Mockito.when(palindromeService.isPalindrome(string)).thenReturn(false);
        boolean checkPalindrome = controller.checkPalindrome("user", string);
        Assertions.assertFalse(checkPalindrome);

    }

    @Test
    void checkPolindromeValidateNumericInputTest() throws IOException {
        String string = "aba1";

        Assertions.assertThrows(ValidationException.class, () -> {
            controller.checkPalindrome("user", string);
        });
    }

    @Test
    void checkPolindromeValidateInputWithSpaceTest() throws IOException {
        String string = "ab a";

        Assertions.assertThrows(ValidationException.class, () -> {
            controller.checkPalindrome("user", string);
        });
    }

}
