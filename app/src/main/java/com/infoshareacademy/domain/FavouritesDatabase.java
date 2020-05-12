package com.infoshareacademy.domain;

import java.util.HashSet;
import java.util.Set;

public final class FavouritesDatabase {

    private static FavouritesDatabase INST_FAVOURITES;
    private Set<String> favouritesIds;

    private FavouritesDatabase() {
        favouritesIds = new HashSet<>();
    }

    public static FavouritesDatabase getInstFavourites() {
        if (INST_FAVOURITES == null) {
            INST_FAVOURITES = new FavouritesDatabase();
        }
        return INST_FAVOURITES;
    }

    public void addAllFavourites(Set<String> favouritesAllId) {
        favouritesIds.addAll(favouritesAllId);
    }

    public void addFavourite(String id) {
        favouritesIds.add(id);
    }

    public Set<String> getFavouritesIds() {
        return this.favouritesIds;
    }
}