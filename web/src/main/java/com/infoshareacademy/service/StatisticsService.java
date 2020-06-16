package com.infoshareacademy.service;


import com.infoshareacademy.domain.Statistics;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.domain.dto.StatisticsView;
import com.infoshareacademy.repository.StatisticsRepositoryBean;
import com.infoshareacademy.service.mapper.FullDrinkMapper;
import com.infoshareacademy.service.mapper.StatisticsMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class StatisticsService {


    @EJB
    private StatisticsRepositoryBean statisticsRepositoryBean;

    @Inject
    private FullDrinkMapper fullDrinkMapper;

    @Inject
    private StatisticsMapper statisticsMapper;

    public void addToStatistics(FullDrinkView fullDrinkView) {
        Statistics statistics = statisticsMapper.toEntity(fullDrinkView);
        long timeStamp = System.currentTimeMillis();

        statistics.setTimeStamp(timeStamp);
        statisticsRepositoryBean.addToStatistics(statistics);

    }

    public List<StatisticsView> getTopDrinks(){
        List<Statistics> statistics = statisticsRepositoryBean.getTopDrinks();
        return statisticsMapper.toView(statistics);
    }





}
