package com.ezgroceries.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Service
public class ShoppingListServiceImpl implements ShoppingListService{

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Override
    public ShoppingListEntity saveShoppingList(ShoppingListEntity shoppingList)
    {
        return shoppingListRepository.save(shoppingList);
    }

    // Read operation
    @Override
    public List<ShoppingListEntity> fetchShoppingListList()
    {
        return (List<ShoppingListEntity>)
                shoppingListRepository.findAll();
    }
    @Override
    public ShoppingListEntity getShoppingListById(UUID shoppingListId)
    {
        return (ShoppingListEntity)
                shoppingListRepository.findById(shoppingListId).get();
    }

    //@Override
    public ShoppingListEntity getCocktails(UUID shoppingListId)
    {
        return (ShoppingListEntity)
                shoppingListRepository.findById(shoppingListId).get();
    }
    // Update operation
    @Override
    public ShoppingListEntity updateShoppingList(ShoppingListEntity shoppingList,UUID shoppingListId)
    {

        return shoppingListRepository.save(shoppingList);
    }

    // Delete operation
    @Override
    public void deleteShoppingListById(UUID shoppingListId)
    {
        shoppingListRepository.deleteById(shoppingListId);
    }
}
