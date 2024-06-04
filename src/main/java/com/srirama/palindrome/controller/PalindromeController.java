package com.srirama.palindrome.controller;

import com.srirama.palindrome.data.AsyncDataStoreService;
import com.srirama.palindrome.model.PalindromeRequest;
import com.srirama.palindrome.service.CacheService;
import com.srirama.palindrome.service.PalindromeService;
import com.srirama.palindrome.validation.ValidationException;
import com.srirama.palindrome.validation.ValidationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * PalindromeController defines the rest service end point
 */
@RestController
@RequestMapping("v1/palindrome")
public class PalindromeController {
    /**
     * The data store service is an asynchronous service which uses a Datastore to store data to persistent store.
     */
    private final AsyncDataStoreService asyncService;
    /**
     * The palindrome service is a service to check if input string in a palindrome.
     */
    private final PalindromeService palindromeService;
    /**
     * cacheService provides access to the cache
     */
    private final CacheService cacheService;
    /**
     * Validation Service allows to configure and perform different validations
     */
    private final ValidationService validationService;

    // This map is used as in memory cache
    private final Map<String, Boolean> map;

    public PalindromeController(AsyncDataStoreService asyncService, PalindromeService palindromeService, CacheService cacheService, ValidationService validationService) {
        this.asyncService = asyncService;
        this.palindromeService = palindromeService;
        this.cacheService = cacheService;
        this.validationService = validationService;
        this.map = cacheService.getCacheObject();
    }

    @PostMapping("/check_palindrome")
    public ResponseEntity<?> checkPalindrome(@Valid @RequestBody PalindromeRequest request) throws IOException {
        boolean isPalindrome = checkPalindrome(request.getUsername(), request.getValue());
        return ResponseEntity.ok(new PalindromeResponse(request, isPalindrome));
    }

    @GetMapping("/check/{userName}/{value}")
    public boolean checkPalindrome(@PathVariable String userName, @PathVariable String value) throws IOException, ValidationException {
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
     *
     * @param value
     */
    public void validateInput(String value) {
        validationService.validate(value);

    }

    public static class PalindromeResponse {
        private final String username;
        private final String value;
        private final boolean isPalindrome;

        public PalindromeResponse(PalindromeRequest palindromeRequest, boolean isPalindrome) {
            this.username = palindromeRequest.getUsername();
            this.value = palindromeRequest.getValue();
            this.isPalindrome = isPalindrome;
        }

        public String getUsername() {
            return username;
        }

        public String getValue() {
            return value;
        }

        public boolean isPalindrome() {
            return isPalindrome;
        }


    }

}
