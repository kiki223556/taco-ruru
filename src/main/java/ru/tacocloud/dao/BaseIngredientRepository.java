package ru.tacocloud.dao;

import java.util.Optional;

import ru.tacocloud.model.Ingredient;

public interface BaseIngredientRepository {
    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
