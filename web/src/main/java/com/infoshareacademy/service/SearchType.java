package com.infoshareacademy.service;

import com.infoshareacademy.domain.dto.FullDrinkView;

import java.util.List;

public class SearchType {

    List<FullDrinkView> drinkViewList;

    int maxPage;

    String queryName;

    public List<FullDrinkView> getDrinkViewList() {
        return drinkViewList;
    }

    public void setDrinkViewList(List<FullDrinkView> drinkViewList) {
        this.drinkViewList = drinkViewList;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }
}
