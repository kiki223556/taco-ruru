package ru.tacocloud.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import ru.tacocloud.model.Ingredient;
import ru.tacocloud.model.IngredientType;
import ru.tacocloud.model.Taco;
import ru.tacocloud.model.TacoOrder;

@Slf4j // Lombok-provided annotation. Automatically generate Logger at compilation.
@Controller // as controller, scanning.
@RequestMapping("/design") // handle requests whose path begins with /design.
@SessionAttributes("tacoOrder") // put into model and maintain
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP),
                new Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP),
                new Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN),
                new Ingredient("CARN", "Carnitas", IngredientType.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES),
                new Ingredient("LETC", "Lettuce", IngredientType.VEGGIES),
                new Ingredient("CHED", "Cheddar", IngredientType.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE),
                new Ingredient("SLSA", "Salsa", IngredientType.SAUCE),
                new Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE));

        IngredientType[] types = IngredientType.values();
        for (IngredientType type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    // 導向至/design時自動觸發
    // design為視圖view得名稱
    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    // 目前沒做啥事，重新導向redirect至其他頁面
    // 用來處理/design template內的form表單所填寫的內容
    // @Valid告訴spring要去驗證內容
    @PostMapping
    public String processTaco(@Valid @ModelAttribute("taco") Taco taco,
            Errors errors) {
        // 假如前端表單格式不對，無法新建taco物件，會產生error
        // 則回到上一頁design處
        if (errors.hasErrors()) {
            // print錯誤訊息
            log.warn(String.format("error: %s", errors.getFieldError()));
            return "design";
        }

        // 成功的話，儲存 taco...
        log.info("Processing taco: " + taco); // 日誌print
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, IngredientType type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
