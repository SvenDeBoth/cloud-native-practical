package com.ezgroceries.shoppinglist;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "SHOPPING_LIST")
/*@SecondaryTable(name = "COCKTAIL_SHOPPING_LIST", pkJoinColumns = @PrimaryKeyJoinColumn(name = "SHOPPING_LIST_ID"))*/
public class ShoppingListEntity {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue
    private UUID shoppingListID;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.PERSIST)
    @JoinTable(name = "SHOPPING_LIST_COCKTAILS", joinColumns = @JoinColumn(name = "shopping_list_entity_id"),inverseJoinColumns = @JoinColumn(name = "COCKTAILS_ID"))
    private Set<CocktailEntity> cocktails = new HashSet<>();

   /* public ShoppingListEntity(UUID shoppingListID, String name, List<UUID> cocktails) {
        this.shoppingListID = shoppingListID;
        this.name = name;
        this.cocktails = cocktails;
    }
*/
    public UUID getShoppingListID() {
        return shoppingListID;
    }

    public void setShoppingListID(UUID shoppingListID) {
        this.shoppingListID = shoppingListID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CocktailEntity> getCocktails() {
        return cocktails;
    }

    public void setCocktails(Set<CocktailEntity> cocktails) {
        this.cocktails = cocktails;
    }
}
