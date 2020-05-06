package com.infoshareacademy.service;

import com.infoshareacademy.domain.FavouritesDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;

public class FavouritesService {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");

    public void loadFavouritesList() {
        FavouritesDatabase favourDatabase = FavouritesDatabase.getInstFavourites();
        Set<String> favourIdSet = favourDatabase.getFavouritesIds();
        String fileName = "Favourites.json";
        try {
            favourIdSet.addAll(JsonReader.jsonFavouritesReader(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        favourDatabase.addAllFavourites(favourIdSet);
    }
}