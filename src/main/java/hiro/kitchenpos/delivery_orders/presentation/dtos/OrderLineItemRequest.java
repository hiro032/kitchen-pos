package hiro.kitchenpos.delivery_orders.presentation.dtos;

import hiro.kitchenpos.delivery_orders.application.dtos.OrderLineItemCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemRequest {

    private UUID menuId;
    private int quantity;

    public OrderLineItemCommand toCommand() {
        return new OrderLineItemCommand(menuId, quantity);
    }
}
