package ru.tacocloud.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import ru.tacocloud.dao.JdbcOrderRepository;
import ru.tacocloud.model.TacoOrder;

@Slf4j // 記錄日誌 -> 編譯時處理
@Controller // 控制器處理前端傳來的請求
@RequestMapping("/orders") // 自己的門牌位置，處理前端要求
public class OrderController {
    private JdbcOrderRepository orderRepo;

    public OrderController(JdbcOrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    // 前端使用get方法，後端對應接收位置/orders/current
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("tacoOrder", new TacoOrder());
        return "orderForm";
    }

    // 因為在orderForm template的表單內把action設為/orders地址
    // 這裡要有method能夠接收
    @PostMapping
    public String processOrder(@Valid TacoOrder order,
            Errors errors,
            SessionStatus sessionStatus) {
        // 假如驗證沒通過，print errors，並將頁面導回order form
        if (errors.hasErrors()) {
            log.warn(String.format("error: %s", errors.getAllErrors()));
            return "orderForm";
        }

        // 若通過驗證，將資料存入資料庫
        orderRepo.save(order);
        sessionStatus.setComplete();

        log.info("Order submitted: " + order); // print log
        return "redirect:/"; // 重新導向至根目錄
    }
}
