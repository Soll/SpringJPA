package com.art.tacocloud.data;

import com.art.tacocloud.taco.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
