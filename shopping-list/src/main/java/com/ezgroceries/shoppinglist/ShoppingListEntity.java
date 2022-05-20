package com.ezgroceries.shoppinglist;


import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
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



}
