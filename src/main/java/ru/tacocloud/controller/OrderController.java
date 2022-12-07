package ru.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.tacocloud.data.OrderRepository;
import ru.tacocloud.model.OrderProps;
import ru.tacocloud.model.auth.User;
import ru.tacocloud.model.taco.TacoOrder;

import javax.validation.Valid;

@Slf4j // 記錄日誌 -> 編譯時處理
@Controller // 控制器處理前端傳來的請求
@RequestMapping("/orders") // 自己的門牌位置，處理前端要求
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepo;
    private OrderProps props;


    @Autowired
    public OrderController(OrderRepository orderRepo,
                            OrderProps props) {
        this.orderRepo = orderRepo;
        this.props = props;
    }

    // 前端使用get方法，後端對應接收位置/orders/current
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("tacoOrder", new TacoOrder());
        return "orderForm";
    }

    // 因為在orderForm template的表單內把action設為/orders地址
    // 這裡要有method能夠接收
    // 儲存order表單
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        // 假如驗證沒通過，print errors
        if (errors.hasErrors()) {
            log.warn(String.format("error: %s", errors.getAllErrors())); // 偵測錯誤
            return "orderForm";
        }

        order.setUser(user);
        orderRepo.save(order);

        log.info("Order submitted: {}", order); // print log

        sessionStatus.setComplete();
        return "redirect:/"; // 重新導向至根目錄
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders",
                orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";

    }
}
