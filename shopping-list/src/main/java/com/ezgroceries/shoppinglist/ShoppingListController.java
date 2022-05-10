package com.ezgroceries.shoppinglist;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ShoppingListController {

    @GetMapping(value = "/shopping-lists", produces = "application/json")
    public ResponseEntity< List <ShoppingListResource>> get(@RequestParam String search) {
        return ResponseEntity.ok((getDummyResources()));
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
        Map<String, UUID> result = new HashMap<String,UUID>();
      for(int i=0;i <cocktailIds.size();i++){
        //  System.out.println(shoppingList.getName());
        //  System.out.println(uuid);
        //  System.out.println(shoppingList.getShoppingListID());
        //  System.out.println(cocktailIds.get(i).toString());
        //  System.out.println(cocktailIds.get(i).getCocktailId());
          shoppingList.getCocktails().add(i,cocktailIds.get(i).getCocktailId());
          }

        return new ResponseEntity<>(cocktailIds, HttpStatus.CREATED);
        //jsonignore
    }


}
