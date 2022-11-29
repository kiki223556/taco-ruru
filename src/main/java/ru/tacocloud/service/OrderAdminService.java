package ru.tacocloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.tacocloud.data.OrderRepository;

@Service
public class OrderAdminService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderAdminService (OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
