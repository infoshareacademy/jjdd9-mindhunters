package com.infoshareacademy.service.validator;

import org.apache.commons.lang3.math.NumberUtils;

import javax.enterprise.context.RequestScoped;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestScoped
public class UserInputValidator {


    public boolean validatePageNumber(String page) {
        Pattern compiledPattern = Pattern.compile("[1-9]+[0-9]*");
        Matcher matcher = compiledPattern.matcher(page);
        return matcher.matches();
    }

    public Long stringToLongConverter(String number) {
        if (NumberUtils.isCreatable(number)) {
            return Long.parseLong(number);
        }
        return -1L;
    }

    public int compareCurrentPageWithMaxPage(int currentPage, int maxPage){
        if (currentPage < 0 || currentPage > maxPage ){
            currentPage = 1;
        }
        return currentPage;
    }

}
