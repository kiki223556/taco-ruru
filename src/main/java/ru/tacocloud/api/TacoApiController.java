package ru.tacocloud.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tacocloud.data.TacoRepository;
import ru.tacocloud.model.taco.Taco;

import java.util.Optional;

@RestController
@RequestMapping(path="/api/tacos", produces="application/json") // 請求輸出格式
@CrossOrigin(origins="http://tacocloud:8080") // 允許來自任何域的客戶端使用api
public class TacoApiController {

    private TacoRepository tacoRepo;

    public TacoApiController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    // 網頁回傳"/api/tacos?recent"
    @GetMapping(params="recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(page).getContent();
    }

    // 透過id獲取單個taco，網頁回傳"/api/tacos?id"
    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // 將使用者建立的taco存於資料庫中
    @PostMapping(consumes="application/json") // 請求輸入格式
    @ResponseStatus(HttpStatus.CREATED) // 告訴客戶端請求成功且資源被創建
    public Taco postTaco(@RequestBody Taco taco) { // 請求本體需轉換成Taco物件，並做為參數傳入
        return tacoRepo.save(taco);
    }


}
