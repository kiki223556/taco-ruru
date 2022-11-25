package ru.tacocloud.data;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ru.tacocloud.model.TacoOrder;

public interface OrderRepository
        // 僅需對aggregate root定義repository藉以persist object，意即儲存tacoOrder便等同於儲存taco
        extends CrudRepository<TacoOrder, Long> {

    // Spring Data透過上下文(DSL)，知道要儲存order，並能辨識findByDeliveryZip方法
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
}
