package com.ezgroceries.shoppinglist;

import java.util.List;
import java.util.UUID;

public interface CocktailService {

    // Save operation
    CocktailEntity saveCocktail(CocktailEntity cocktail);

    // Read operation
    List<CocktailEntity> fetchCocktailList();
    // Read operation
    CocktailEntity getCocktailById(UUID cocktailID);
    // Update operation
    CocktailEntity updateCocktail(CocktailEntity cocktail,
                                UUID cocktailId);
    List<CocktailEntity> findByDrinkId(String chars);
    // Delete operation


    void deleteCocktailById(UUID cocktailId);
}
