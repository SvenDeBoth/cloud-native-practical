package com.ezgroceries.shoppinglist;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {
    @Autowired
    private CocktailDBClient cocktailDBClient;
    
    @GetMapping
    public ResponseEntity< List <CocktailResource>> get(@RequestParam String search) {
        
        CocktailDBResponse callResponse = cocktailDBClient.searchCocktails(search);
        //System.out.println(callResponse.toString());
        List<CocktailResource> cocktailResourceList = new ArrayList<CocktailResource>();
// loop over call response , add to list cocktailresource
        for(int i=0;i <callResponse.getDrinks().size();i++){
        CocktailResource singleCocktailResource = new CocktailResource(UUID.randomUUID(),callResponse.getDrinks().get(i).getStrDrink(),callResponse.getDrinks().get(i).getStrGlass(),callResponse.getDrinks().get(i).getStrInstructions(),callResponse.getDrinks().get(i).getStrDrinkThumb(),callResponse.getDrinks().get(i).getIngredientsList());
        //System.out.println(singleCocktailResource.toString());

        cocktailResourceList.add(singleCocktailResource);
        }
        return ResponseEntity.ok(cocktailResourceList);
    }

    public static List<CocktailResource> getDummyResources() {
        return Arrays.asList(
                new CocktailResource(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), "Margerita",
                        "Cocktail glass",
                        "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                        "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt")),
                new CocktailResource(
                        UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita",
                        "Cocktail glass",
                        "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                        "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                        Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt")));
    }

}
