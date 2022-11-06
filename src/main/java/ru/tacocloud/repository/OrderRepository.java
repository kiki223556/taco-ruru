package ru.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;

import ru.tacocloud.model.TacoOrder;

public interface OrderRepository
                extends CrudRepository<TacoOrder, Long> {
}
