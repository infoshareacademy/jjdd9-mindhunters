package com.infoshareacademy.service;

import com.infoshareacademy.domain.Rating;
import com.infoshareacademy.repository.RatingRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.function.Supplier;

@Stateless
public class RatingService {

    @EJB
    private RatingRepository ratingRepository;


    public Rating getRatingByDrinkId(Long drinkId){

        return ratingRepository.findByDrinkId(drinkId).orElse(createEmptyRating());

    }

    private Rating createEmptyRating() {

        Rating initialRating = new Rating();
        initialRating.setNumberOfRatings(0L);
        initialRating.setSum(0L);

        return initialRating;
    }

}
