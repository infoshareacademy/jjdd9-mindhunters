package com.infoshareacademy.service.validator;

import javax.enterprise.context.RequestScoped;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestScoped
public class UserInputValidator {


    public boolean validateSpecialChars(String input){
        Pattern compiledPattern = Pattern.compile("[a-zA-Z0-9_ -]{2,30}");
        Matcher matcher = compiledPattern.matcher(input);
        return matcher.matches();
    }

    public boolean validatePageNumber(String page){
        Pattern compiledPattern = Pattern.compile("\\d*");
        Matcher matcher = compiledPattern.matcher(page);
        return matcher.matches();
    }

}
