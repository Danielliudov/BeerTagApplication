package com.example.daniel.beertagappfrontend.validators.base;

import com.example.daniel.beertagappfrontend.utils.enums.ErrorCode;

public interface LoginValidator {

    ErrorCode isUsernameValid(String input);

    ErrorCode isPasswordValid(String input, String confirmPassword);
}
