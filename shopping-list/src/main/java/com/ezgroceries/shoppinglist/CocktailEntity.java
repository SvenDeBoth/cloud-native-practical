package com.ezgroceries.shoppinglist;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cocktail")
public class CocktailEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
// conversion ?
    public Set<String> getIngredients() {
        return ingredients;
    }

    @Column(name = "id_drink", nullable = false)
    private String id_drink;

    @Column(name = "name", nullable = false)
    private String name;


    @Convert(converter = StringSetConverter.class)
    @Column(name = "ingredients", nullable = false)
    private Set<String> ingredients;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    @Converter
    public class StringSetConverter implements AttributeConverter<Set<String>, String> {

        @Override
        public String convertToDatabaseColumn(Set<String> set) {
            if(!CollectionUtils.isEmpty(set)) {
                return "," + String.join(",", set) + ",";
            } else {
                return null;
            }
        }

        @Override
        public Set<String> convertToEntityAttribute(String joined) {
            if(joined != null) {
                String values = joined.substring(1, joined.length() - 1); //Removes leading and trailing commas
                return new HashSet<>(Arrays.asList(values.split(",")));
            }
            return new HashSet<>();
        }
    }
}
