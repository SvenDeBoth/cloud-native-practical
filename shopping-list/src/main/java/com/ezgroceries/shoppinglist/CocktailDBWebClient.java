package com.ezgroceries.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;


@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1", fallback = CocktailDBWebClient.CocktailDBClientFallback.class)
public interface CocktailDBWebClient {

    @GetMapping(value = "search.php")
    CocktailDBWebResponse searchCocktails(@RequestParam("s") String search);

    @Component
    class CocktailDBClientFallback implements CocktailDBWebClient {

        @Autowired
        private final CocktailService cocktailService;

        public CocktailDBClientFallback(CocktailService cocktailService) {
            this.cocktailService = cocktailService;
        }

        @Override
        public CocktailDBWebResponse searchCocktails(String search) {
            List<CocktailEntity> cocktailEntities = cocktailService.findByNameContainingIgnoreCase(search);

            CocktailDBWebResponse cocktailDBResponse = new CocktailDBWebResponse();
            cocktailDBResponse.setDrinks(cocktailEntities.stream().map(cocktailEntity -> {
                CocktailDBWebResponse.DrinkResource drinkResource = new CocktailDBWebResponse.DrinkResource();
                drinkResource.setIdDrink(cocktailEntity.getId_drink());
                drinkResource.setStrDrink(cocktailEntity.getName());
                drinkResource.setStrDrinkThumb(cocktailEntity.getImage());
                drinkResource.setStrGlass(cocktailEntity.getGlass());
                drinkResource.setStrInstructions(cocktailEntity.getInstructions());
             //   drinkResource.setStrIngredient1(cocktailEntity.getIngredients().get(1));
                return drinkResource;
            }).collect(Collectors.toList()));

            return cocktailDBResponse;
        }
    }
}