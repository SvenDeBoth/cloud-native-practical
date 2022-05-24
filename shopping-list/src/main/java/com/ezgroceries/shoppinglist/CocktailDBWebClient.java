package com.ezgroceries.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
                List<String> ingredientsList=new ArrayList<>(cocktailEntity.getIngredients());
                System.out.println("ingr1");
                if(ingredientsList.size() >= 1){drinkResource.setStrIngredient1(ingredientsList.get(0));}
                System.out.println("ingr2");
                if(ingredientsList.size() >= 2){drinkResource.setStrIngredient2(ingredientsList.get(1));}
                System.out.println("ingr3");
                if(ingredientsList.size() >= 3){drinkResource.setStrIngredient3(ingredientsList.get(2));}
                System.out.println("ingr4");
                if(ingredientsList.size() >= 4){drinkResource.setStrIngredient4(ingredientsList.get(3));}
                System.out.println("ingr5");
                if(ingredientsList.size() >= 5){drinkResource.setStrIngredient5(ingredientsList.get(4));}
                System.out.println("ingr5");
                if(ingredientsList.size() >= 6){drinkResource.setStrIngredient6(ingredientsList.get(5));}
                System.out.println("ingr7");
                if(ingredientsList.size() >= 7){drinkResource.setStrIngredient7(ingredientsList.get(6));}
                System.out.println(ingredientsList.get(1));
               return drinkResource;
            }).collect(Collectors.toList()));

            return cocktailDBResponse;
        }
    }
}