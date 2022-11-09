package ru.tacocloud.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.tacocloud.model.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
}
