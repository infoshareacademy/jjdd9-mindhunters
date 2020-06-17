package com.infoshareacademy.domain;


import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "Statistics.getTop10Drinks",
                query = "SELECT s.drink, COUNT(s.drink) FROM Statistics s GROUP BY s.drink ORDER BY " +
                        "COUNT" +
                        "(s" +
                        ".drink)  DESC "),

        @NamedQuery(
                name = "Statistics.getTopCategories",
                query = "SELECT s.drink, COUNT(s.drink) FROM Statistics s GROUP BY s.drink ORDER BY " +
                        "COUNT" +
                        "(s" +
                        ".drink)  DESC ")


})

@Entity
@Table(name = "statistics")
public class Statistics {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id")
    private Drink drink;


    @Column(name = "time_stamp")
    private Long timeStamp;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
