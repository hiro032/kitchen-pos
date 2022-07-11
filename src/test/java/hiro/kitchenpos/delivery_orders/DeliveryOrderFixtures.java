package hiro.kitchenpos.delivery_orders;

import hiro.kitchenpos.delivery_orders.presentation.dtos.DeliveryOrdersRequest;
import hiro.kitchenpos.delivery_orders.presentation.dtos.OrderLineItemRequest;

import java.util.List;
import java.util.UUID;

public class DeliveryOrderFixtures {

    private static final String DEFAULT_DELIVERY_ADDRESS = "서울 서초구 중앙로 626";

    public static DeliveryOrdersRequest deliveryOrdersRequest(final OrderLineItemRequest... orderLineItemRequest) {
        return new DeliveryOrdersRequest(DEFAULT_DELIVERY_ADDRESS, List.of(orderLineItemRequest));
    }

    public static OrderLineItemRequest orderLineItemRequest(final UUID menuId, final int quantity) {
        return new OrderLineItemRequest(menuId, quantity);
    }
}
