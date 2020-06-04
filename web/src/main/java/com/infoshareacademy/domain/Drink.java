package com.infoshareacademy.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NamedQueries({
        @NamedQuery(
                name = "Drink.findDrinkByIngredients",
                query = "select distinct d from Drink d join d.drinkIngredients di where di.ingredient IN " +
                        ":ingredients "),
        @NamedQuery(
                name = "Drink.countDrinkByIngredients",
                query = "select distinct COUNT(d) from Drink d join d.drinkIngredients di where di.ingredient IN " +
                        ":ingredients "),
        @NamedQuery(
                name = "Drink.findDrinkByPartialName",
                query = "SELECT d FROM Drink d WHERE LOWER( d.drinkName) LIKE LOWER(:partialDrinkName)"),

        @NamedQuery(
                name = "Drink.countDrinksByPartialName",
                query = "SELECT COUNT(d) FROM Drink d WHERE LOWER( d.drinkName) LIKE LOWER(:partialDrinkName)"),

        @NamedQuery(
                name = "Drink.findAll",
                query = "SELECT d FROM Drink d"
        ),
        @NamedQuery(
                name = "Drink.findTotalDrinksAmount",
                query = "SELECT COUNT(d) FROM Drink d"),

        @NamedQuery(
                name = "Drink.findDrinksByCategories",
                query = "select d from Drink d where d.category.id in (:category)"
        ),
        @NamedQuery(
                name = "Drink.findDrinksByCategories.count",
                query = "select count (d) from Drink d where d.category.id in (:category)"
        )
})

@Entity
@Table(name = "drink")
public class Drink {
//DTO simpleDrinkView - pola tylko wymagane bez interackji z baza - do listy
//DTO full wymaga wszystkich danych - do drinka

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_id")
    private String drinkId;

    @Column(name = "name")
    @NotNull
    private String drinkName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "alcohol_status")
    @NotNull
    private String alcoholStatus;

    @NotNull
    private String recipe;

    @OneToMany(mappedBy = "drinkId", fetch = FetchType.LAZY)
    private List<DrinkIngredient> drinkIngredients = new ArrayList<>();

    private String image;

    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(String drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAlcoholStatus() {
        return alcoholStatus;
    }

    public void setAlcoholStatus(String alcoholStatus) {
        this.alcoholStatus = alcoholStatus;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public List<DrinkIngredient> getDrinkIngredients() {
        return drinkIngredients;
    }

    public void setDrinkIngredients(List<DrinkIngredient> drinkIngredients) {
        this.drinkIngredients = drinkIngredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}