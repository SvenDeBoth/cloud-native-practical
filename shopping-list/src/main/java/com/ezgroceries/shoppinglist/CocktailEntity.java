package com.ezgroceries.shoppinglist;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.*;

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

    public String getId_drink() {
        return id_drink;
    }

    public void setId_drink(String id_drink) {
        this.id_drink = id_drink;
    }

    /*  @ManyToOne
        ShoppingListEntity shoppingList;
    */
    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailID) {
        this.cocktailId = cocktailID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
/*
    public ShoppingListEntity getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingListEntity shoppingList) {
        this.shoppingList = shoppingList;
    }
*/
    /*   public CocktailEntity(UUID cocktailId, String name, String glass, String instructions, String image, Set ingredients) {
        this.cocktailId = cocktailId;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
        this.ingredients = ingredients;
    }*/
   /*  CocktailEntity()
    {

    }*/
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






  /*  @Id

    private UUID id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
// conversion ?
    public Set<String> getIngredients() {
        return ingredients;
    }

    @Column(name = "id_drink", nullable = false)
    private String id_drink;


    private String name;



    private Set<String> ingredients;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }






   */
}
