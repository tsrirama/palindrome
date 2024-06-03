package com.example.demo;

import org.springframework.stereotype.Service;

/**
 * Service to check for palindrome.
 */
@Service
public class PalindromeService {

	public boolean isPalindrome(String value) {

    	String reverse = new StringBuilder(value).reverse().toString();
    	return value.equals(reverse);
	}
}
