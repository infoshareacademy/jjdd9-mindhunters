package com.infoshareacademy.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(
                name = "Category.findAllNames",
                query = "SELECT c.name FROM Category c"
        ),
        @NamedQuery(
                name = "Category.getByName",
                query = "SELECT c FROM Category c where c.name= :name"
        )
})
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
