package com.infoshareacademy.cdi;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinksDatabase;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public final class DrinksDatabaseBean {
    @EJB
    DrinksDatabase drinksDatabase;



    public List<Drink> getAllDrinks (){
         return DrinksDatabase.getINSTANCE().getDrinks();
    }

}