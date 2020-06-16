package com.infoshareacademy.service;


import com.infoshareacademy.domain.Statistics;
import com.infoshareacademy.domain.dto.FullDrinkView;
import com.infoshareacademy.repository.StatisticsRepositoryBean;
import com.infoshareacademy.service.mapper.FullDrinkMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class StatisticsService {


    @EJB
    private StatisticsRepositoryBean statisticsRepositoryBean;

    @Inject
    private FullDrinkMapper fullDrinkMapper;

    public void addToStatistics(FullDrinkView fullDrinkView) {
        Statistics statistics = fullDrinkMapper.toStatisticsEntity(fullDrinkView);
        long timeStamp = System.currentTimeMillis();

        statistics.setTimeStamp(timeStamp);
        statisticsRepositoryBean.addToStatistics(statistics);

    }


}
