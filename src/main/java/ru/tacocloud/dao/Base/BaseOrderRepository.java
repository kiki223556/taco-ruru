package ru.tacocloud.dao.Base;

import ru.tacocloud.model.TacoOrder;

// 存tacoorder時也得存taco
public interface BaseOrderRepository {
    TacoOrder save(TacoOrder order);
}
