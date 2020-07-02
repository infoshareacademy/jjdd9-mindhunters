package com.infoshareacademy.domain;

import javax.persistence.*;

@Entity
@Table(name = "drink_rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numberOfRatings;

    private Long sum;

    @OneToOne
    @JoinColumn(name = "drink_id", unique = true)
    private Drink drink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(Long numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }
}
