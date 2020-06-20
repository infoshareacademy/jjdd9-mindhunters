package com.infoshareacademy.email;

import com.infoshareacademy.domain.dto.FullDrinkView;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EmailBuildStrategy {

    String createContent(List<FullDrinkView> drinks);

}
