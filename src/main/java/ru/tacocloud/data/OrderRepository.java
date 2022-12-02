package ru.tacocloud.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.tacocloud.model.auth.User;
import ru.tacocloud.model.taco.TacoOrder;

public interface OrderRepository
        // 僅需對aggregate root定義repository藉以persist object，意即儲存tacoOrder便等同於儲存taco
        extends CrudRepository<TacoOrder, Long> {

    // Spring Data透過上下文(DSL)，能辨識方法
    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

}
