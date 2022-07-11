package hiro.kitchenpos.delivery_orders.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderLineItemCommand {

    private UUID menuId;
    private int quantity;

}
