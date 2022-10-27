package ru.tacocloud.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {
    // 新增表單限制
    // 當要創建taco物件時必須符合條件
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name; //只回傳taco名稱，所以用string

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;  //可能是0到多選，所以用list
}
