package ru.tacocloud.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ru.tacocloud.model.auth.User;
import ru.tacocloud.model.taco.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> findByUserOrderByPlacedAtDesc(
            User user, Pageable pageable);
}
