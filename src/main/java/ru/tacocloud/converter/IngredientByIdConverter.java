package ru.tacocloud.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.tacocloud.dao.IngredientRepository;
import ru.tacocloud.model.Ingredient;

// 假如前端form內傳向後端資料為string型態，為將其轉換為ingredient型態
// 使用converter轉換型值 string id -> ingredient
// 使用@Component使該類可被視為bean，被spring所使用
// Spring boot 自動配置會找到她得，並且會儲存至register內等待使用
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
        private IngredientRepository ingredientRepo;

        @Autowired
        public IngredientByIdConverter(IngredientRepository ingredientRepo) {
                this.ingredientRepo = ingredientRepo;
        }

        // 簡化converter
        @Override
        public Ingredient convert(String id) {
                return ingredientRepo.findById(id).orElse(null);
        }
}

