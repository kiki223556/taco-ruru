package ru.tacocloud.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import ru.tacocloud.model.taco.Ingredient;
import ru.tacocloud.model.taco.IngredientType;
import ru.tacocloud.model.taco.Taco;
import ru.tacocloud.model.taco.TacoOrder;
import ru.tacocloud.repository.IngredientRepository;

@Slf4j // Lombok提供的註解，在編譯時自動生成紀錄，是一種日誌記錄的框架。
@Controller // 可被掃瞄並被辨識為controller，Spring將自動生成@DesignTacoController的實例bean。
@RequestMapping("/design") // 使此controller能處理路徑為("/design")的請求。
@SessionAttributes("tacoOrder") // 存取tacoOrder資料，並在此會話範圍內維護資料(完成訂單需要很多步驟，直到完成前都要記住當前操作)。
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        // fetch all ingredients from the database
        List<Ingredient> ingredients = Lists.newArrayList(ingredientRepo.findAll());

        IngredientType[] types = IngredientType.values();
        for (IngredientType type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    // 將它命名為tacoOrder，並放進資料裡，使@SessionAttributes可以使用
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    // 跟@RequestMapping一組，當接收到http("/design")請求，執行showDesignForm()回傳("design")頁面。
    // 導向至/design時自動觸發
    // design為視圖view得名稱
    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    // 處理form POST請求
    // 當表單提交時，表單內存著Taco物件特性，並將Taco作為參數傳入方法
    // 此處方法將taco作為addTaco()參數加入tacoOrder物件，並記錄
    @PostMapping
    public String processTaco(Taco taco,
            @ModelAttribute TacoOrder tacoOrder) {
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, IngredientType type) {
        return ingredients
                .stream() // 將list創建為stream，以聲明的方式處理數據。
                .filter(x -> x.getType().equals(type)) // filter()：過濾stream裡的元素。
                .collect(Collectors.toList()); // toList()：將一個stream回傳成list的資料型態。
    }

    public boolean isTaco(Taco taco) {
        return taco.getName() == "";
    }
}
