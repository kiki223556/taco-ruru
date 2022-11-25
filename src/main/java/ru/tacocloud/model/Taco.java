package ru.tacocloud.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Taco {

    // 依賴資料庫自動產生ID值
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 新增表單限制
    // 當要創建taco物件時必須符合條件
    @NotNull // name不可為空
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name; // 只回傳taco名稱，所以用string

    // 創建的時間
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient") // ingredient至少為1
    @ManyToMany // 宣告Taco和Ingredient list關聯
    private List<Ingredient> ingredients = new ArrayList<>();  // 可能是0到多選，所以用list

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
