package ru.tacocloud.model;

//lombok：語法糖，在編譯時自動生成執行所需JavaBean methods，包含@Getter, @Setter, @ToString等。
import lombok.Data;

//@Data：生成一個建構子，自動生成@Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor。
//使其可接收下列final的特性，可消除冗長的code和提高開發效率。
@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final IngredientType type;
}
