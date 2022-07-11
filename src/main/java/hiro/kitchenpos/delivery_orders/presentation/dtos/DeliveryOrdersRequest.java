package hiro.kitchenpos.delivery_orders.presentation.dtos;

import hiro.kitchenpos.delivery_orders.application.dtos.CreateDeliveryOrderCommand;
import hiro.kitchenpos.delivery_orders.application.dtos.OrderLineItemCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOrdersRequest {

    private String deliveryAddress;
    private List<OrderLineItemRequest> orderLineItemRequests;

    public CreateDeliveryOrderCommand toCommand() {
        List<OrderLineItemCommand> orderLineItemCommands = orderLineItemRequests.stream()
                .map(OrderLineItemRequest::toCommand)
                .collect(Collectors.toList());

        return new CreateDeliveryOrderCommand(deliveryAddress, orderLineItemCommands);
    }
}
