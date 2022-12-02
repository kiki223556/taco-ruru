package ru.tacocloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.tacocloud.data.IngredientRepository;
import ru.tacocloud.data.UserRepository;
import ru.tacocloud.model.taco.Ingredient;
import ru.tacocloud.model.taco.IngredientType;

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

    // preloading data
    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo,
                                        UserRepository userRepo, PasswordEncoder encoder) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.save(new Ingredient("FLTO", "Flour Torilla", IngredientType.WRAP));
                repo.save(new Ingredient("COTO", "Corn Torilla", IngredientType.WRAP));
                repo.save(new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN));
                repo.save(new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN));
                repo.save(new Ingredient("TMTO", "Tomatoes", IngredientType.VEGGIES));
                repo.save(new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES));
                repo.save(new Ingredient("CHED", "Cheddar", IngredientType.CHEESE));
                repo.save(new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE));
                repo.save(new Ingredient("SLSA", "Salsa", IngredientType.SAUCE));
                repo.save(new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE));
            }
        };
    }
}
