package hiro.kitchenpos.delivery_orders.domain;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryOrderRepository {

    DeliveryOrder save(DeliveryOrder deliveryOrder);

    Optional<DeliveryOrder> findById(UUID id);

}
