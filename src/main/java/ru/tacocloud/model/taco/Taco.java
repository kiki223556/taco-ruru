package ru.tacocloud.model.taco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt = new Date();

    // 新增表單限制
    // 當要創建taco物件時必須符合條件
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name; // 只回傳taco名稱，所以用string

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @ManyToMany()
    private List<Ingredient> ingredients = new ArrayList<>(); // 可能是1到多選，所以用list

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
