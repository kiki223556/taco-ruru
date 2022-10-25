package ru.tacocloud.converter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.tacocloud.model.Ingredient;
import ru.tacocloud.model.IngredientType;

// 假如前端form內傳向後端資料為string型態，為將其轉換為ingredient型態
// 使用converter轉換型值 string -> ingredient
// 使用map -> key, value配對，可在瞬間配對，查找成功
// converter需implement spring內的converter接口
// 使用@Component使該類可被視為bean，被spring所使用
// Spring boot 自動配置會找到她得，並且會儲存至register內等待使用
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    // 尚未有數據庫，所以用map來手動建立假資料
    public IngredientByIdConverter() {
        ingredientMap.put("FLTO",
                new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP));
        ingredientMap.put("COTO",
                new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP));
        ingredientMap.put("GRBF",
                new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN));
        ingredientMap.put("CARN",
                new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN));
        ingredientMap.put("TMTO",
                new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES));
        ingredientMap.put("LETC",
                new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES));
        ingredientMap.put("CHED",
                new Ingredient("CHED", "Cheddar", IngredientType.CHEESE));
        ingredientMap.put("JACK",
                new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE));
        ingredientMap.put("SLSA",
                new Ingredient("SLSA", "Salsa", IngredientType.SAUCE));
        ingredientMap.put("SRCR",
                new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE));
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
