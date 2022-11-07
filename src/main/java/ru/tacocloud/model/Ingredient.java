package ru.tacocloud.model;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data：生成一個建構子，自動生成@Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor。
// 使其可接收下列final的特性，可消除冗長的code和提高開發效率。
// JPA requires that entities have a no-arguments constructor
// final attribute setting -> force = true
// settign default value of null, 0, or false
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    private final IngredientType type;
}
