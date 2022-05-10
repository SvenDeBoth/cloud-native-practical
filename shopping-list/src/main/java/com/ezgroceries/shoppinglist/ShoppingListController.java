package com.ezgroceries.shoppinglist;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ShoppingListController {

    @GetMapping(value = "/shopping-lists", produces = "application/json")
    public ResponseEntity< List <ShoppingListResource>> listShoppingList(@RequestParam String search) {
        return ResponseEntity.ok((getDummyResources()));
    }
    @GetMapping(value = "/shopping-lists/{uuid}", produces = "application/json")
    public ResponseEntity<ShoppingListIngredients> searchShoppingList(@PathVariable("uuid") UUID uuid) {
        // loop all coctails + ingredients , store all ingredients in ingredientsOverview
        // uuid = shoppinglist UUID
        // list all names of coctailingredients , output shopplinglistid , name , ingredients
       // System.out.println(shoppingLists.toString());
        ShoppingListResource shoppingList = shoppingLists.get(uuid);
       // System.out.println(uuid);
        ShoppingListIngredients ingredientsOverview = new ShoppingListIngredients();
        ingredientsOverview.setShoppingListID(shoppingList.getShoppingListID());
       // System.out.println(ingredientsOverview.getShoppingListID());
        ingredientsOverview.setshoppingListName(shoppingList.getName());
       // System.out.println(ingredientsOverview.getshoppingListName());
        System.out.println(shoppingList.getCocktails().size());
        System.out.println(CocktailController.getDummyResources().size());
        int k=0;
        for(int i=0;i <shoppingList.getCocktails().size();i++){
            // shoppingList.get(i).getCocktails() --> uuid of cocktails
            for (int j = 0; j < CocktailController.getDummyResources().size(); j++)
            {
                CocktailResource s = CocktailController.getDummyResources().get(j);
              //  System.out.println("loop1");
              //  System.out.println(CocktailController.getDummyResources().get(j).getCocktailId());
              //  System.out.println(CocktailController.getDummyResources().get(j).getIngredients().get(1).toString());
               // System.out.println(shoppingList.getCocktails().get(i));
                if (CocktailController.getDummyResources().get(j).getCocktailId().equals(shoppingList.getCocktails().get(i)))
                { // add all ingredients
                //    System.out.println("loop2");
                //    System.out.println();
                //    System.out.println(CocktailController.getDummyResources().get(j).getName());
                //    System.out.println(CocktailController.getDummyResources().get(j).getIngredients().size());
                    for (int l = 0; l < CocktailController.getDummyResources().get(j).getIngredients().size(); l++)
                    {
                 //       System.out.println(CocktailController.getDummyResources().get(j).getIngredients().get(l).toString());
                        ingredientsOverview.getcocktailIngredients().add(CocktailController.getDummyResources().get(j).getIngredients().get(l).toString());
                  //      System.out.println("added");

                    }
                }
            }


        }


        return new ResponseEntity<>(ingredientsOverview, HttpStatus.FOUND);
    }



    private List<ShoppingListResource> getDummyResources() {
        return Arrays.asList(
                new ShoppingListResource(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c5"), "Sven",Arrays.asList(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"))),
                new ShoppingListResource(
                        UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab074"), "Sven2",Arrays.asList(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"))
                        ));
    }

    Map<UUID, ShoppingListResource> shoppingLists = new HashMap<UUID, ShoppingListResource>();

    @PostMapping(value = "/shopping-lists")
    public ResponseEntity<ShoppingListResource> createShoppingList(@RequestBody ShoppingListResource newShoppingList) {
        var inputData = new ShoppingListResource(UUID.randomUUID(),newShoppingList.getName(), new ArrayList());
        shoppingLists.put(inputData.getShoppingListID(), inputData);
        return new ResponseEntity<>(inputData, HttpStatus.CREATED);
    }

    @PostMapping(value = "/shopping-lists/{uuid}/cocktails")
    @JsonView(value = CocktailResourceView.CocktailView.Post.class)
    public ResponseEntity<List<CocktailResource>> addCocktails(@PathVariable("uuid") UUID uuid, @RequestBody List<CocktailResource> cocktailIds) {
        ShoppingListResource shoppingList = shoppingLists.get(uuid);
      //  Map<String, UUID> result = new HashMap<String,UUID>();
      for(int i=0;i <cocktailIds.size();i++){
        //  System.out.println(shoppingList.getName());
        //  System.out.println(uuid);
        //  System.out.println(shoppingList.getShoppingListID());
        //  System.out.println(cocktailIds.get(i).toString());
        //  System.out.println(cocktailIds.get(i).getCocktailId());
          shoppingList.getCocktails().add(i,cocktailIds.get(i).getCocktailId());
          }

        return new ResponseEntity<>(cocktailIds, HttpStatus.CREATED);
    }


}
