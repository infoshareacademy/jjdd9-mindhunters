package com.infoshareacademy.service;


import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Statistics;
import com.infoshareacademy.domain.dto.DrinkLiveSearchView;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.repository.StatisticsRepositoryBean;
import com.infoshareacademy.service.mapper.DrinkLiveSearchMapper;
import com.infoshareacademy.service.mapper.StatisticsMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class StatisticsService {


    @EJB
    private StatisticsRepositoryBean statisticsRepositoryBean;

    @EJB
    private DrinkLiveSearchMapper drinkLiveSearchMapper;

    @Inject
    private StatisticsMapper statisticsMapper;

    public void addToStatistics(FullDrinkView fullDrinkView) {
        Statistics statistics = statisticsMapper.toEntity(fullDrinkView);
        long timeStamp = System.currentTimeMillis();

        statistics.setTimeStamp(timeStamp);
        statisticsRepositoryBean.addToStatistics(statistics);

    }

    public Map<String, Long> getTopDrinks() {
        List statistics = statisticsRepositoryBean.getTopDrinks();
        Object[] row;
        Map<String, Long> statisticsMap = new HashMap<>();

        for (Object o : statistics) {

            if (o instanceof Object[]) {

                row = (Object[]) o;

                if (row[0] instanceof Drink) {

                    DrinkLiveSearchView drinkLiveSearchView = drinkLiveSearchMapper.toView((Drink) row[0]);
                    statisticsMap.put(drinkLiveSearchView.getDrinkName(), (Long) row[1]);

                }
            }
        }

        return statisticsMap;
    }

    public Map<String, Long> getTopCategories() {
        List statistics = statisticsRepositoryBean.getTopCategories();
        Object[] row;
        Map<String, Long> statisticsMap = new HashMap<>();

        for (Object o : statistics) {

            if (o instanceof Object[]) {

                row = (Object[]) o;

                if (row[0] instanceof Drink) {

                    DrinkLiveSearchView drinkLiveSearchView = drinkLiveSearchMapper.toView((Drink) row[0]);
                    statisticsMap.put(drinkLiveSearchView.getDrinkName(), (Long) row[1]);

                }
            }
        }

        return statisticsMap;
    }


}
