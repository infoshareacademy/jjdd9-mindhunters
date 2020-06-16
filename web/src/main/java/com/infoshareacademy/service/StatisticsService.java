package com.infoshareacademy.service;


import com.infoshareacademy.domain.Statistics;
import com.infoshareacademy.repository.StatisticsRepositoryBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class StatisticsService {


    @EJB
    private StatisticsRepositoryBean statisticsRepositoryBean;


    public Statistics addToStatistics(Statistics statistics){

        statisticsRepositoryBean.addToStatistics(statistics);

        return statistics;
    }

}
