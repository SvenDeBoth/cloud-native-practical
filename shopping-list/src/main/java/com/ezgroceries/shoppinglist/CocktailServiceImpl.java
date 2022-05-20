package com.ezgroceries.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CocktailServiceImpl implements CocktailService{

    @Autowired
    private CocktailRepository cocktailRepository;

    @Override
    public CocktailEntity saveCocktail(CocktailEntity cocktailService)
    {
        return cocktailRepository.save(cocktailService);
    }

    // Read operation
    @Override
    public List<CocktailEntity> fetchCocktailList()
    {
        return (List<CocktailEntity>)
                cocktailRepository.findAll();
    }
    // Read operation
    @Override
    public List<CocktailEntity> findByDrinkId(String chars)
    {
        return (List<CocktailEntity>)
                cocktailRepository.findByDrinkId(chars);
    };
    @Override
    public List<CocktailEntity> findByNameContainingIgnoreCase(String name)
    {
        return (List<CocktailEntity>)
                cocktailRepository.findByNameContainingIgnoreCase(name);
    };
    // Update operation

    @Override
    public CocktailEntity getCocktailById(UUID cocktailID)
    {
        return (CocktailEntity)
                cocktailRepository.findById(cocktailID).get();
    };
    @Override
    public CocktailEntity     updateCocktail(CocktailEntity cocktail,UUID cocktailId)
    {
        CocktailEntity cocktailDB
                = cocktailRepository.findById(cocktailId)
                .get();

        if (Objects.nonNull(cocktail.getName())
                && !"".equalsIgnoreCase(
                cocktail.getName())) {
            cocktailDB.setName(
                    cocktail.getName());
        }

        // add update for external ID_drink, glass , instructions, image , ingredients


        return cocktailRepository.save(cocktailDB);
    }

    // Delete operation
    @Override
    public void deleteCocktailById(UUID cocktailId)
    {
        cocktailRepository.deleteById(cocktailId);
    }


/*

    public List<CocktailEntity> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {
        //Get all the idDrink attributes
        List<String> ids = drinks.stream().map(CocktailDBResponse.DrinkResource::getIdDrink).collect(Collectors.toList());

        //Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, CocktailEntity> existingEntityMap = CocktailService. (ids).stream().collect(Collectors.toMap(CocktailEntity::getId_drink, o -> o, (o, o2) -> o));

        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        Map<String, CocktailEntity> allEntityMap = drinks.stream().map(drinkResource -> {
            CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
            if (cocktailEntity == null) {
                CocktailEntity newCocktailEntity = new CocktailEntity();
                newCocktailEntity.setCocktailId(UUID.randomUUID());
                newCocktailEntity.setId_drink(drinkResource.getIdDrink());
                newCocktailEntity.setName(drinkResource.getStrDrink());
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getId_drink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<CocktailResource> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailEntity> allEntityMap) {
        return drinks.stream().map(drinkResource -> new CocktailResource(allEntityMap.get(drinkResource.getIdDrink()).getCocktailId(), drinkResource.getStrDrink(), drinkResource.getStrGlass(),
                drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), getIngredients(drinkResource))).collect(Collectors.toList());
    }

*/
}
