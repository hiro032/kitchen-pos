package hiro.kitchenpos.delivery_orders.infrastructure;

import hiro.kitchenpos.delivery_orders.domain.DeliveryOrder;
import hiro.kitchenpos.delivery_orders.domain.DeliveryOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaDeliveryOrderRepository extends JpaRepository<DeliveryOrder, UUID>, DeliveryOrderRepository {
}
