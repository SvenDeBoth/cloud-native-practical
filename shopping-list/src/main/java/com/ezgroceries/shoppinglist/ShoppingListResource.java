package com.ezgroceries.shoppinglist;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingListResource {
   private UUID shoppingListID;

    private String name;

    private List<UUID> cocktails = new ArrayList<>();

    public ShoppingListResource(UUID shoppingListID, String name, List<UUID> cocktails) {
        this.shoppingListID = shoppingListID;
        this.name = name;
        this.cocktails = cocktails;
    }

    public UUID getShoppingListID() {
        return shoppingListID;
    }

    public void setShoppingListID(UUID shoppingListID) {
        this.shoppingListID = shoppingListID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UUID> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<UUID> cocktails) {
        this.cocktails = cocktails;
    }

}
