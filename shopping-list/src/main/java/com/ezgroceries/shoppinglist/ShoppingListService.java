package com.ezgroceries.shoppinglist;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ShoppingListService {

    // Save operation
    ShoppingListEntity saveShoppingList(ShoppingListEntity shoppingList);

    // Read operation
    List<ShoppingListEntity> fetchShoppingListList();

    ShoppingListEntity getShoppingListById(UUID shoppingListId);
    // Update operation
    ShoppingListEntity updateShoppingList(ShoppingListEntity shoppingList,
                                UUID shoppingListId);

    // Delete operation
    void deleteShoppingListById(UUID shoppingListId);
}
