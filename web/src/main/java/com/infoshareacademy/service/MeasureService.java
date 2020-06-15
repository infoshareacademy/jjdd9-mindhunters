package com.infoshareacademy.service;

import com.infoshareacademy.domain.Measure;
import com.infoshareacademy.repository.MeasureRepositoryBean;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MeasureService {

    @Inject
    private MeasureRepositoryBean measureRepositoryBean;

    public Measure getOrCreate(String quantity) {
        Measure measure = measureRepositoryBean.getByQuantity(quantity);
        if (measure == null) {
            measure = new Measure();
            measure.setQuantity(quantity);
            measureRepositoryBean.save(measure);
        }
        return measure;
    }
}
