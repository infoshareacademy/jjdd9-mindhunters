package com.infoshareacademy.service;

import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.servlet.SingleViewServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class SearchTypeService {


    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTypeService.class.getName());

    @EJB
    private DrinkService drinkService;


    public Map<String, Object> singleViewSearchType(Long drinkId){
        Map<String, Object> dataModel = new HashMap<>();

        if (drinkId < 0) {
            dataModel.put("errorMessage", "Wrong input.\n");
            LOGGER.debug("Negative drink id");
        } else {
            final FullDrinkView foundDrinkById = drinkService.findDrinkById(drinkId);

            if (foundDrinkById == null) {
                dataModel.put("errorMessage", "Drink not found.\n");
                LOGGER.debug("Drink id not found");
            }

            dataModel.put("drink", foundDrinkById);
        }
        return dataModel;
    }
}
