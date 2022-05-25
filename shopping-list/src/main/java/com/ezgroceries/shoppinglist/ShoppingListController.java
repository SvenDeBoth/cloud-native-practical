package com.ezgroceries.shoppinglist;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ShoppingListController {
    @Autowired private ShoppingListService shoppingListService;

    @GetMapping(value = "/shopping-lists", produces = "application/json")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<?> listShoppingList(@RequestParam(required = false) String search) {
        if (search != null)
        {
            System.out.println(" request param --> getsingleshoppinglist");
            return ResponseEntity.ok(searchShoppingList(UUID.fromString(search)));
        }
        else
        {
            List<ShoppingListIngredients> allShoppingIngredientsLists = new ArrayList<ShoppingListIngredients>();
            List<ShoppingListEntity> allShoppingLists = shoppingListService.fetchShoppingListList();
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            for (int i=0;i <allShoppingLists.size();i++)

            {
                System.out.println(allShoppingLists.get(i).getUid_create());
                System.out.println(username);
                System.out.println(allShoppingLists.get(i).getUid_create().equals(username));
                if (allShoppingLists.get(i).getUid_create().equals(username) ) {
                    System.out.println("add to output");
                allShoppingIngredientsLists.add(shoppingListService.searchShoppingListLogic(allShoppingLists.get(i).getShoppingListID()));
            }
            }

            return ResponseEntity.ok((allShoppingIngredientsLists));
        }

    }

    @GetMapping(value = "/shopping-lists/{uuid}", produces = "application/json")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ShoppingListIngredients> searchShoppingList(@PathVariable("uuid") UUID uuid) {


        return new ResponseEntity<>(shoppingListService.searchShoppingListLogic(uuid), HttpStatus.OK);
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
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ShoppingListEntity> createShoppingList(@RequestBody ShoppingListEntity newShoppingList) {
       /* var inputData = new ShoppingListEntity(UUID.randomUUID(),newShoppingList.getName(), new ArrayList());
        shoppingLists.put(inputData.getShoppingListID(), inputData);*/

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }
        //String username =  "John";
        System.out.println(username);
        newShoppingList.setUid_create(username);
        shoppingListService.saveShoppingList(newShoppingList);
        return new ResponseEntity<>(newShoppingList, HttpStatus.CREATED);
    }

    @PostMapping(value = "/shopping-lists/{uuid}/cocktails")
    @PreAuthorize("hasRole('CLIENT')")
    @JsonView(value = CocktailEntityView.CocktailView.Post.class)
    public ResponseEntity<List<CocktailEntity>> addCocktails(@PathVariable("uuid") UUID uuid, @RequestBody List<CocktailEntity> cocktailIds) {

        ShoppingListEntity shoppingList = shoppingListService.addCocktails(uuid,cocktailIds);
        List<CocktailEntity> cocktailIdsResult = new ArrayList<>(shoppingList.getCocktails());
        return new ResponseEntity<>(cocktailIdsResult, HttpStatus.CREATED);
    }


}
