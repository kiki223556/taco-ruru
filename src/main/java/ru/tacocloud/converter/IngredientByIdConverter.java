package ru.tacocloud.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.tacocloud.data.IngredientRepository;
import ru.tacocloud.model.taco.Ingredient;

// 假如前端form內傳向後端資料為string型態，為將其轉換為ingredient型態
// 使用converter轉換型值 string -> ingredient
// 使用map -> key, value配對，可在瞬間配對，查找成功
// converter需implement spring內的converter接口
// 使用@Component使該類可被視為bean，被spring所使用
// Spring boot 自動配置會找到她得，並且會儲存至register內等待使用
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo; }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null); }
}

