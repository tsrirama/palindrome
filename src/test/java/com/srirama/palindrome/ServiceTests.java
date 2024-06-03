package com.srirama.palindrome;

import java.io.IOException;

import com.srirama.palindrome.service.PalindromeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTests {

	@InjectMocks
	PalindromeService service;

	@Test
	void checkPolindromeTest() throws IOException {
		String string = "aba";

		boolean checkPalindrome = service.isPalindrome(string);
		Assertions.assertTrue(checkPalindrome);

	}
	@Test
	void checkNotPolindromeTest() throws IOException {
		String string = "abaa";

		boolean checkPalindrome = service.isPalindrome(string);
		Assertions.assertFalse(checkPalindrome);

	}

}
