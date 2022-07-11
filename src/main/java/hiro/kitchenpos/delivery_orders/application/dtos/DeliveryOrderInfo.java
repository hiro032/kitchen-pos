
package hiro.kitchenpos.delivery_orders.application.dtos;

import hiro.kitchenpos.delivery_orders.application.dtos.OrderLineItemInfo.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class DeliveryOrderInfo {

    @Data
    @Builder
    public static class CreateDeliveryOrderInfo {

        private UUID id;
        private String orderStatus;
        private String deliveryAddress;
        private LocalDateTime orderDateTime;
        private List<CreateOrderLineItemInfo> orderLineItemsInfo;

    }

}
