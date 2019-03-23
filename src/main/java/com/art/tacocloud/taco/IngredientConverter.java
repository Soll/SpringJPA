package com.art.tacocloud.taco;


import com.art.tacocloud.data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String id) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(x -> ingredients.add(x));

        for (Ingredient ingredient : ingredients)
            if (ingredient.getId().equals(id))
                return ingredient;

        return null;
    }
}
