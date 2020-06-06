package com.infoshareacademy.service.validator;

import javax.enterprise.context.RequestScoped;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestScoped
public class UserInputValidator {


    public boolean validateSpecialChars(String input){
        String inputSingleSpaces = removeExtraSpaces(input);
        Pattern compiledPattern = Pattern.compile("[a-zA-Z0-9_ -]{2,30}");
        Matcher matcher = compiledPattern.matcher(inputSingleSpaces);
        return matcher.matches();
    }

    private String removeExtraSpaces(String input) {
        return input.replaceAll(" +", " ");
    }

    public boolean validatePageNumber(String page){
        Pattern compiledPattern = Pattern.compile("\\d*");
        Matcher matcher = compiledPattern.matcher(page);
        return matcher.matches();
    }

}
