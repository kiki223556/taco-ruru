package ru.tacocloud.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@Entity
public class Taco {

    @Id
    private Long id;
    private Date createdAt = new Date();

    // 新增表單限制
    // 當要創建taco物件時必須符合條件
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name; // 只回傳taco名稱，所以用string

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients; // 可能是0到多選，所以用list
}
