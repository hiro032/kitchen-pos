package hiro.kitchenpos.delivery_orders.application.dtos;

import hiro.kitchenpos.delivery_orders.domain.OrderLineItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

public class OrderLineItemInfo {

    @Data
    @Builder
    @AllArgsConstructor
    public static class CreateOrderLineItemInfo {

        private UUID menuId;
        private int quantity;

        public static CreateOrderLineItemInfo toEntity(final OrderLineItem entity) {
            return new CreateOrderLineItemInfo(entity.getMenuId(), entity.getQuantity());
        }
    }
    
}
