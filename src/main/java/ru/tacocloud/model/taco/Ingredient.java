package ru.tacocloud.model.taco;

//lombok：語法糖，在編譯時自動生成執行所需JavaBean methods，包含@Getter, @Setter, @ToString等。
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

// @Data：生成一個建構子，自動生成@Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor。
// 使其可接收下列final的特性，可消除冗長的code和提高開發效率。
@Data
@Entity // 宣告此為JPA的實例
@AllArgsConstructor // 使創建Ingredient物件具有初始值
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true) // 建構子設為private,final及預設值。
public class Ingredient {

    @Id // 在資料庫中被辨識為獨一無二的實例
    private String id;
    private String name;
    private IngredientType type;
}


