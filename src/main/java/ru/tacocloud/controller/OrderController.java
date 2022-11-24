package ru.tacocloud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import ru.tacocloud.model.auth.User;
import ru.tacocloud.model.taco.TacoOrder;
import ru.tacocloud.repository.OrderRepository;

@Slf4j // 記錄日誌 -> 編譯時處理
@Controller // 控制器處理前端傳來的請求
@RequestMapping("/orders") // 自己的門牌位置，處理前端要求
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
            SessionStatus sessionStatus,
            @AuthenticationPrincipal User user) {
        // 假如驗證沒通過，print errors，並將頁面導回order form
        if (errors.hasErrors()) {
            log.warn(String.format("error: %s", errors.getAllErrors()));
            return "orderForm";
        }

        // Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication();
        // User user = (User) authentication.getPrincipal();

        order.setUser(user);

        // 若通過驗證，將資料存入資料庫
        orderRepository.save(order);
        sessionStatus.setComplete();

        log.info("Order submitted: " + order); // print log
        return "redirect:/"; // 重新導向至根目錄
    }
}
