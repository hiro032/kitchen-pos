package hiro.kitchenpos.delivery_orders.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateDeliveryOrderCommand {

    private String deliveryAddress;
    private List<OrderLineItemCommand> orderLineItemCommands;

}
