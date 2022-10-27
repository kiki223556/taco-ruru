package ru.tacocloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller的註解使這個class能被自動掃描到，並被建立一個名為HomePageController的實例bean
//也可以用其他註解來讓程式自動掃描到這個組件，但controller的命名較符合他的存在描述意義
@Controller
public class HomePageController {

    //@GetMapping：當接收到路徑("/")的http GET請求時，執行home()方法，並回傳相對應view的名稱
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
