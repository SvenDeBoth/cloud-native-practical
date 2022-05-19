package com.ezgroceries.shoppinglist;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ShoppingListController {
    @Autowired private ShoppingListService shoppingListService;
    @Autowired private CocktailService cocktailService;
    @GetMapping(value = "/shopping-lists", produces = "application/json")
    public ResponseEntity<?> listShoppingList(@RequestParam(required = false) String search) {
        if (search != null)
        {
            System.out.println(" request param --> getdummyresources");
            return ResponseEntity.ok(searchShoppingList(UUID.fromString(search)));
        }
        else
        {
            List<ShoppingListIngredients> allShoppingIngredientsLists = new ArrayList<ShoppingListIngredients>();
            List<ShoppingListEntity> allShoppingLists = shoppingListService.fetchShoppingListList();

            for (int i=0;i <allShoppingLists.size();i++)
            {
                allShoppingIngredientsLists.add(searchShoppingListLogic(allShoppingLists.get(i).getShoppingListID()));

            }

            return ResponseEntity.ok((allShoppingIngredientsLists));
        }

    }
public ShoppingListIngredients searchShoppingListLogic(UUID uuid) {
    // loop all coctails + ingredients , store all ingredients in ingredientsOverview
    // uuid = shoppinglist UUID
    // list all names of coctailingredients , output shopplinglistid , name , ingredients
    // System.out.println(shoppingLists.toString());
    ShoppingListEntity shoppingList = shoppingListService.getShoppingListById(uuid);
    // System.out.println(uuid);
    ShoppingListIngredients ingredientsOverview = new ShoppingListIngredients();
    ingredientsOverview.setShoppingListID(shoppingList.getShoppingListID());

    ingredientsOverview.setshoppingListName(shoppingList.getName());

    System.out.println(shoppingList.getCocktails().size());
    // System.out.println(CocktailController.getDummyResources().size());
    int k = 0;
    for (int i = 0; i < shoppingList.getCocktails().size(); i++) {
        // shoppingList.get(i).getCocktails() --> uuid of cocktails
        List<CocktailEntity> cocktailList = new ArrayList<>(shoppingList.getCocktails());

        for (int j = 0; j < cocktailList.size(); j++) {
            ingredientsOverview.getcocktailIngredients().addAll(cocktailList.get(j).getIngredients());

        }
        List<String> ingredientsWithoutDup = ingredientsOverview.getcocktailIngredients().stream()
                .distinct()
                .collect(Collectors.toList());
        ingredientsOverview.getcocktailIngredients().clear();
        ingredientsOverview.getcocktailIngredients().addAll(ingredientsWithoutDup);

    }
    return(ingredientsOverview);
}
    @GetMapping(value = "/shopping-lists/{uuid}", produces = "application/json")
    public ResponseEntity<ShoppingListIngredients> searchShoppingList(@PathVariable("uuid") UUID uuid) {


        return new ResponseEntity<>(searchShoppingListLogic(uuid), HttpStatus.OK);
    }



   /* private List<ShoppingListResource> getDummyResources() {
        return Arrays.asList(
                new ShoppingListResource(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c5"), "Sven",Arrays.asList(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"))),
                new ShoppingListResource(
                        UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab074"), "Sven2",Arrays.asList(UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"))
                        ));
    }
*/
  //  Map<UUID, ShoppingListResource> shoppingLists = new HashMap<UUID, ShoppingListResource>();

    @PostMapping(value = "/shopping-lists")
    public ResponseEntity<ShoppingListEntity> createShoppingList(@RequestBody ShoppingListEntity newShoppingList) {
       /* var inputData = new ShoppingListEntity(UUID.randomUUID(),newShoppingList.getName(), new ArrayList());
        shoppingLists.put(inputData.getShoppingListID(), inputData);*/
        shoppingListService.saveShoppingList(newShoppingList);
        return new ResponseEntity<>(newShoppingList, HttpStatus.CREATED);
    }

    @PostMapping(value = "/shopping-lists/{uuid}/cocktails")
    @JsonView(value = CocktailEntityView.CocktailView.Post.class)
    public ResponseEntity<List<CocktailEntity>> addCocktails(@PathVariable("uuid") UUID uuid, @RequestBody List<CocktailEntity> cocktailIds) {
        ShoppingListEntity shoppingList = shoppingListService.getShoppingListById(uuid);
        //shoppingList.setCocktails(new HashSet<>(cocktailIds));
        List<CocktailEntity> cocktailList = new ArrayList<>(shoppingList.getCocktails());
      //  Map<String, UUID> result = new HashMap<String,UUID>();
          for(int i=0;i <cocktailIds.size();i++){
        //  System.out.println(shoppingList.getName());
        //  System.out.println(uuid);
        //  System.out.println(shoppingList.getShoppingListID());
        //  System.out.println(cocktailIds.get(i).toString());
        //  System.out.println(cocktailIds.get(i).getCocktailId());
              //cocktailIds.get(i).getCocktailId()
              //shoppingList.getCocktails(i).
              Boolean found = false;
              for(int j=0;j <cocktailList.size();j++) {
                  System.out.println(cocktailIds.get(i).getCocktailId().toString());
                  System.out.println(cocktailList.get(j).getCocktailId().toString());
                  if (cocktailIds.get(i).getCocktailId().toString().equals(cocktailList.get(j).getCocktailId().toString()) ){
                      found = true;
                      break;

                  }
              }
              if (found == false)
              {System.out.println(cocktailIds.get(i).getCocktailId() + "added in list");
                  shoppingList.getCocktails().add(cocktailIds.get(i));
              }
              else
              { System.out.println(cocktailIds.get(i).getCocktailId() + "already in list");}


                }
        shoppingListService.updateShoppingList(shoppingList, uuid);

        return new ResponseEntity<>(cocktailIds, HttpStatus.CREATED);
    }


}
