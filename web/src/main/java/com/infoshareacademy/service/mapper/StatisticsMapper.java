package com.infoshareacademy.service.mapper;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.Statistics;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.StatisticsView;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class StatisticsMapper {

    @Inject
    private CategoryMapper categoryMapper;

    @Inject
    private FullDrinkMapper fullDrinkMapper;

    public Statistics toEntity(FullDrinkView fullDrinkView) {
        Drink drink = new Drink();
        drink.setId(fullDrinkView.getId());
        drink.setDrinkId(fullDrinkView.getDrinkId());
        drink.setDrinkName(fullDrinkView.getDrinkName());
        drink.setCategory(categoryMapper.toEntity(fullDrinkView.getCategoryView()));

        Statistics statistics = new Statistics();
        statistics.setDrink(drink);
        return statistics;
    }

    public StatisticsView toView(Statistics statistics) {

        StatisticsView statisticsView = new StatisticsView();
        statisticsView.setId(statistics.getId());
        statisticsView.setFullDrinkView(fullDrinkMapper.toView(statistics.getDrink()));
        statisticsView.setTimeStamp(statistics.getTimeStamp());

        return statisticsView;
    }


    public List<StatisticsView> toView(List<Statistics> statisticsList) {

        List<StatisticsView> statisticsViews = new ArrayList<>();

        for (Statistics statistics : statisticsList) {
            statisticsViews.add(toView(statistics));
        }

        return statisticsViews;
    }
}
