package com.ezgroceries.shoppinglist;




import com.fasterxml.jackson.annotation.JsonView;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CocktailResource {
    @JsonView(value = {CocktailResourceView.CocktailView.Post.class,  CocktailResourceView.CocktailView.PUT.class})
    private UUID cocktailId;

    @JsonView(value = {CocktailResourceView.CocktailView.PUT.class})
   private String name;

    private String glass;

    private String instructions;

    private String image;

    private List ingredients;

    public CocktailResource(UUID cocktailId, String name, String glass, String instructions, String image, List ingredients) {
        this.cocktailId = cocktailId;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
        this.ingredients = ingredients;
    }

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

    public List getIngredients() {
        return ingredients;
    }

    public void setIngredients(List ingredients) {
        this.ingredients = ingredients;
    }


    @Override
    public String toString() {
        return "CocktailResource{" +
                "cocktailID=" + cocktailId +
                ", name='" + name + '\'' +
                ", glass='" + glass + '\'' +
                ", instructions='" + instructions + '\'' +
                ", image='" + image + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

}
