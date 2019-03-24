package com.art.tacocloud.taco;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;


    @ManyToMany
    @JoinTable(
            name = "Taco_Ingredients",
            joinColumns = @JoinColumn(name = "TACO_ID"),
            inverseJoinColumns = @JoinColumn(name = "INGREDIENTS_ID")
    )
    @Size(min = 1, message = "Name must be at least 1 ingredient")
    private List<Ingredient> ingredients;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @PrePersist
    void createAt() {
        this.createdAt = new Date();
    }

}
