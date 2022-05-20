package com.ezgroceries.shoppinglist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CocktailRepository extends JpaRepository<CocktailEntity , UUID> {
    @Query("select c from CocktailEntity c where c.id_drink = ?1")
    List<CocktailEntity> findByDrinkId(String chars);
    List<CocktailEntity> findByNameContainingIgnoreCase(String name);
}
