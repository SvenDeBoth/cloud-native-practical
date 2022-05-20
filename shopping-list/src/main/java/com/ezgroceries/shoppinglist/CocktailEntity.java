package com.ezgroceries.shoppinglist;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.util.*;
// @data will generate getters and setters
@Data
@Entity
@Table(name = "cocktail")
public class CocktailEntity {
    @Column(name = "id", nullable = false)
    @Id
    @JsonView(value = {CocktailEntityView.CocktailView.Post.class,  CocktailEntityView.CocktailView.PUT.class})
    @GeneratedValue
    private UUID cocktailId;

    @Column(name = "name", nullable = true)
    @JsonView(value = {CocktailEntityView.CocktailView.PUT.class})
    @GeneratedValue
    private String name;

    /* external id of cocktail */
    @Column(name = "id_drink", nullable = true)
    private String id_drink;

    @Column(name = "glass", nullable = true)
    private String glass;

    @Column(name = "instructions", nullable = true)
    private String instructions;

    @Column(name = "image", nullable = true)
    private String image;


    @Convert(converter = StringSetConverter.class)
    @Column(name = "ingredients", nullable = true)
    private Set<String> ingredients;


   /* @Override
    public String toString() {
        return "CocktailResource{" +
                "cocktailID=" + cocktailId +
                ", name='" + name + '\'' +
                ", glass='" + glass + '\'' +
                ", instructions='" + instructions + '\'' +
                ", image='" + image + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }*/








}
