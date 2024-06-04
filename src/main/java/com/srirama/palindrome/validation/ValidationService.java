package com.srirama.palindrome.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * the autowired list of validators allows the flexibility  to add more validation as needed in future
 */
@Service
public class ValidationService {

    private final List<InputValidator> validatorList;

    public ValidationService(List<InputValidator> validatorList) {
        this.validatorList = validatorList;
    }

    public void validate(String input) {
        validatorList.forEach(inputValidator -> inputValidator.validate(input));
    }
}

