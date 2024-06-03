package com.srirama.palindrome.validation;

import org.springframework.stereotype.Component;

/**
 * This validator checks for space and numbers in the input
 * and will be autowired to the lost of validators in the validationService
 */
@Component
public class NoSpaceAndNumberValidator implements InputValidator {

    /**
     * @param input
     * @return
     */
    @Override
    public void validate(String input) {
        if (input.contains(" ") || input.matches(".*\\d.*")) {
            throw new ValidationException("Value passed contains spaces or number, kindly pass String types");
        }
    }
}
