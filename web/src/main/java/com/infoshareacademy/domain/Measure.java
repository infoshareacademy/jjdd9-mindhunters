package com.infoshareacademy.domain;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "measure")
@Transactional
public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
