package ru.tacocloud.model.taco;

import lombok.Data;

// 用來連結taco and ingredient之間的關係
// 不用特地加上@Table -> 因為他只是兩張表中間的生成物
// spring will automatically deal with it
@Data
public class IngredientRef {

    private final String ingredient;
}

