package hiro.kitchenpos.delivery_orders.presentation.dtos;

import hiro.kitchenpos.delivery_orders.application.dtos.OrderLineItemInfo.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class DeliveryOrderResponse {

    @Data
    @Builder
    public static class  CreateDeliveryOrderResponse {
        private UUID id;
        private String orderStatus;
        private String deliveryAddress;
        private LocalDateTime orderDateTime;
        private List<CreateOrderLineItemInfo> orderLineItemsInfo;
    }

}
