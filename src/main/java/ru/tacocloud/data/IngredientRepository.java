package ru.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.tacocloud.model.taco.Ingredient;

// CrudRepository內已定義三種抽象方法findAll,findById,save，不需再寫一次
// repository須extend spring data interface，spring data才會幫interface repository自動生成實例
public interface IngredientRepository

        // 參數為<type of persist object, type of object's ID field>
        extends CrudRepository<Ingredient, String> {
}
