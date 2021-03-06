package com.example.daniel.beertagappfrontend.validators;

import com.example.daniel.beertagappfrontend.utils.Constants;
import com.example.daniel.beertagappfrontend.utils.enums.ErrorCode;
import com.example.daniel.beertagappfrontend.validators.base.LoginValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterValidator implements LoginValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN =
            ("^" + "(?=.*[a-zA-Z])" +       //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 4 characters
                    "$");


    public RegisterValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }


    @Override
    public ErrorCode isUsernameValid(String input) {
        if (input.isEmpty()) return ErrorCode.USERNAME_NULL;
        if (input.length() < Constants.USERNAME_MIN_LENGTH || input.length() > Constants.USERNAME_MAX_LENGTH)
            return ErrorCode.USERNAME_TOO_SIMPLE;
        else return ErrorCode.USERNAME_OK;
    }

    @Override
    public ErrorCode isPasswordValid(String input, String confirmPassword) {
        if (input.isEmpty()) return ErrorCode.PASSWORD_NULL;
        else if (!validatePassword(input)) return ErrorCode.PASSWORD_TOO_SIMPLE;
        else if (!input.equals(confirmPassword)) return ErrorCode.PASSWORDS_DONT_MATCH;
        else return ErrorCode.PASSWORD_OK;
    }

    private boolean validatePassword(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
