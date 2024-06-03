package com.example.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

/**
 * Controller defines the rest service end point
 */
@RestController
@RequestMapping("v1/palindrome")
public class Controller {
	/**
	 * The data store service is an asynchronous service which uses a Datastore to store data to persistent store.
	 */
	@Autowired
	private AsyncDataStoreService asyncService;
	/**
	 * The palindrome service is a service to check if input string in a palindrome.
	 */
	@Autowired
	PalindromeService palindromeService;
	/**
	 * DataStore provides access to the persistent store
	 */
	@Autowired
	private DataStore dataStore;
	/**
	 * Validation Service allows to configure and perform different validations
	 */
	@Autowired
	ValidationService validationService;

	// This map is used as in memory cache
	static Map<String, Boolean> map;

	@GetMapping("/check/{userName}/{value}")
	public boolean checkPalindrome(@PathVariable String userName, @PathVariable String value) throws IOException {
		if(null==map) {
			//if there is no cache yet, it may be initiated
			map = new HashMap<String, Boolean>();
		}
		//Check if there was already an entry in cache for the user.
		if (map.containsKey(value)) {
			return map.get(value);
		}
		//validate the input before calling the palindrome service
		validateInput(value);
		//call the palindrome service
		boolean check = palindromeService.isPalindrome(value);
		//update the cache
		map.put(value, check);
		// use the asynchronous service to update the persistent store
		asyncService.saveToDataStore(value, check);

		return check;

	}

	/**
	 * ValidateInput calls the validation service
	 * @param value
	 */
	public void validateInput(String value) {
		validationService.validate(value);

	}

	/**
	 * The method loads the cache from the persistent store on startup
	 */
	@PostConstruct
	private void loadCacheFromFile() {
		map = dataStore.getDataForCache();
	}

}
