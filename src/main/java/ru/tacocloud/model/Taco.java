package ru.tacocloud.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {

    private Date createdAt = new Date();

    // 新增表單限制
    // 當要創建taco物件時必須符合條件
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name; // 只回傳taco名稱，所以用string

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients = new ArrayList<>(); // 可能是1到多選，所以用list

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
