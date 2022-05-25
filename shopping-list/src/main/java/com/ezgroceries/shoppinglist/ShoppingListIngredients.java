package com.ezgroceries.shoppinglist;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingListIngredients {
    private UUID shoppingListID;

    private String shoppingListName;

    private List<String> cocktailIngredients = new ArrayList<>();
    private String uid_create;
    public ShoppingListIngredients(UUID shoppingListID, String shoppingListName, List<String> cocktailIngredients , String uid_create) {
        this.shoppingListID = shoppingListID;
        this.shoppingListName = shoppingListName;
        this.cocktailIngredients = cocktailIngredients;
        this.uid_create = uid_create;
    }

    public ShoppingListIngredients() {

    }

    public UUID getShoppingListID() {
        return shoppingListID;
    }

    public void setShoppingListID(UUID shoppingListID) {
        this.shoppingListID = shoppingListID;
    }

    public String getshoppingListName() {
        return shoppingListName;
    }

    public void setshoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }

    public List<String> getcocktailIngredients() {
        return cocktailIngredients;
    }

    public void setCocktailIngredients(List<String> cocktailIngredients) {
        this.cocktailIngredients = cocktailIngredients;
    }

    public String getUid_create() {
        return uid_create;
    }

    public void setUid_create(String uid_create) {
        this.uid_create = uid_create;
    }
}
