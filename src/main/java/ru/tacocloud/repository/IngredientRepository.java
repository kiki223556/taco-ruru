package ru.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;

import ru.tacocloud.model.taco.Ingredient;

// 繼承crud 勝了許多Code
public interface IngredientRepository
                extends CrudRepository<Ingredient, String> {

}
