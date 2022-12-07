package ru.tacocloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.tacocloud.data.IngredientRepository;
import ru.tacocloud.data.TacoRepository;
import ru.tacocloud.data.UserRepository;
import ru.tacocloud.model.taco.Ingredient;
import ru.tacocloud.model.taco.IngredientType;
import ru.tacocloud.model.taco.Taco;

import java.util.Arrays;

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

    // fake data
    @Bean
    public CommandLineRunner dataLoader(
            IngredientRepository repo,
            UserRepository userRepo,
            PasswordEncoder encoder,
            TacoRepository tacoRepo) {
        return args -> {
            Ingredient flourTortilla = new Ingredient(
                    "FLTO", "Flour Tortilla", IngredientType.WRAP);
            Ingredient cornTortilla = new Ingredient(
                    "COTO", "Corn Tortilla", IngredientType.WRAP);
            Ingredient groundBeef = new Ingredient(
                    "GRBF", "Ground Beef", IngredientType.PROTEIN);
            Ingredient carnitas = new Ingredient(
                    "CARN", "Carnitas", IngredientType.PROTEIN);
            Ingredient tomatoes = new Ingredient(
                    "TMTO", "Diced Tomatoes", IngredientType.VEGGIES);
            Ingredient lettuce = new Ingredient(
                    "LETC", "Lettuce", IngredientType.VEGGIES);
            Ingredient cheddar = new Ingredient(
                    "CHED", "Cheddar", IngredientType.CHEESE);
            Ingredient jack = new Ingredient(
                    "JACK", "Monterrey Jack", IngredientType.CHEESE);
            Ingredient salsa = new Ingredient(
                    "SLSA", "Salsa", IngredientType.SAUCE);
            Ingredient sourCream = new Ingredient(
                    "SRCR", "Sour Cream", IngredientType.SAUCE);
            repo.save(flourTortilla);
            repo.save(cornTortilla);
            repo.save(groundBeef);
            repo.save(carnitas);
            repo.save(tomatoes);
            repo.save(lettuce);
            repo.save(cheddar);
            repo.save(jack);
            repo.save(salsa);
            repo.save(sourCream);
            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(
                    flourTortilla, groundBeef, carnitas,
                    sourCream, salsa, cheddar));
            tacoRepo.save(taco1);
            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(
                    cornTortilla, groundBeef, cheddar,
                    jack, sourCream));
            tacoRepo.save(taco2);
            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(
                    flourTortilla, cornTortilla, tomatoes,
                    lettuce, salsa));
            tacoRepo.save(taco3);
        };
    }
}


// preloading data
  /*  @Bean
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
}*/

