package com.infoshareacademy.service.validator;

import org.apache.commons.lang3.math.NumberUtils;

import javax.enterprise.context.RequestScoped;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestScoped
public class UserInputValidator {


    public boolean validateSpecialChars(String input) {
        Pattern compiledPattern = Pattern.compile("[a-zA-Z0-9_ -]{2,30}");
        Matcher matcher = compiledPattern.matcher(input);
        return matcher.matches();
    }

    public String removeExtraSpaces(String input) {
        return input.replaceAll(" +", " ");
    }

    public boolean validatePageNumber(String page) {
        Pattern compiledPattern = Pattern.compile("\\d*");
        Matcher matcher = compiledPattern.matcher(page);
        return matcher.matches();
    }

    public Long stringToLongConverter(String number) {
        if (NumberUtils.isCreatable(number)) {
            return Long.parseLong(number);
        }
        return -1L;
    }

}
