package com.ezgroceries.shoppinglist;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {
    @Autowired
    private CocktailDBWebClient cocktailDBWebClient;
    @Autowired private CocktailService cocktailService;
    @GetMapping
    public ResponseEntity< List <CocktailEntity>> get(@RequestParam String search) {
        // collect from website
        CocktailDBWebResponse callResponse = cocktailDBWebClient.searchCocktails(search);
        //System.out.println(callResponse.toString());
        List<CocktailEntity> cocktailEntityList = new ArrayList<CocktailEntity>();
        System.out.println(callResponse.getDrinks().size());

// loop over call response , add to list cocktailresource
        for(int i=0;i <callResponse.getDrinks().size();i++){

            System.out.println("adding "+ i);


            // Transform data and add to outputlist of db
        CocktailEntity singleCocktailEntity = new CocktailEntity();
            singleCocktailEntity.setCocktailId(UUID.randomUUID());
            singleCocktailEntity.setName(callResponse.getDrinks().get(i).getStrDrink());
            singleCocktailEntity.setId_drink(callResponse.getDrinks().get(i).getIdDrink());
            singleCocktailEntity.setGlass(callResponse.getDrinks().get(i).getStrGlass());
            singleCocktailEntity.setImage(callResponse.getDrinks().get(i).getStrDrinkThumb());
            singleCocktailEntity.setInstructions(callResponse.getDrinks().get(i).getStrInstructions());
            System.out.println(callResponse.getDrinks().get(i).getIngredientsList());
            System.out.println(callResponse.getDrinks().get(i).toString());
            singleCocktailEntity.setIngredients(new HashSet<>(callResponse.getDrinks().get(i).getIngredientsList()));
        //System.out.println(singleCocktailResource.toString());

            cocktailEntityList.add(singleCocktailEntity);
            // check if exist in db ,
            List<CocktailEntity> cocktailFromDB =  cocktailService.findByDrinkId(callResponse.getDrinks().get(i).getIdDrink()) ;
            if (cocktailFromDB.size() == 0)
                { // if not found , add to database
                    System.out.println("adding to database");
                           cocktailService.saveCocktail(singleCocktailEntity);
                }




        }

        // return data from database
        return ResponseEntity.ok(cocktailEntityList);
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
