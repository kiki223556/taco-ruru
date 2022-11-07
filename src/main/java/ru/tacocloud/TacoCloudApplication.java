package ru.tacocloud;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ru.tacocloud.model.Ingredient;
import ru.tacocloud.model.IngredientType;
import ru.tacocloud.repository.IngredientRepository;

/*
@SpringBootApplication為executable JAR運行時的切入點，包含以下3個註解
    @SpringBootConfiguration 添加配置
    @EnableAutoConfiguration 自動配置
    @ComponentScan 宣告註解時，能自動掃描發現並註冊
*/
@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    // they are using the repositories to create the persisted objects instead of an
    // SQL script
    // they’ll work equally well for relational databases or non-relational
    // databases
    @Bean
    public ApplicationRunner dataLoader(IngredientRepository repo) {
        return args -> {
            repo.save(new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", IngredientType.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", IngredientType.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE));
        };
    }

}
