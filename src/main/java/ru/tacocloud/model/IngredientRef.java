package ru.tacocloud.model;

import lombok.Data;

// 用來連結taco and ingredient之間的關係
@Data
public class IngredientRef {
    private final String ingredient;
}
