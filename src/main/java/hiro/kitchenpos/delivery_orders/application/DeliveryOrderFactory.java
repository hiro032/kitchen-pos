package hiro.kitchenpos.delivery_orders.application;

import hiro.kitchenpos.delivery_orders.application.dtos.CreateDeliveryOrderCommand;
import hiro.kitchenpos.delivery_orders.application.dtos.OrderLineItemCommand;
import hiro.kitchenpos.delivery_orders.domain.DeliveryOrder;
import hiro.kitchenpos.delivery_orders.domain.DeliveryOrderStatus;
import hiro.kitchenpos.delivery_orders.domain.OrderLineItem;
import hiro.kitchenpos.menu.domain.Menu;
import hiro.kitchenpos.menu.domain.MenuRepository;
import hiro.kitchenpos.menu.domain.exception.MenuNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DeliveryOrderFactory {

    private final MenuRepository menuRepository;

    public DeliveryOrder createDeliveryOrder(final CreateDeliveryOrderCommand command) {

        List<OrderLineItem> orderLineItems = new ArrayList<>();

        for (OrderLineItemCommand orderLineItemCommand : command.getOrderLineItemCommands()) {
            Menu menu = menuRepository.findById(orderLineItemCommand.getMenuId())
                    .orElseThrow(MenuNotFoundException::new);

            BigDecimal orderLineItemPrice = menu.getPrice().getPrice().multiply(BigDecimal.valueOf(orderLineItemCommand.getQuantity()));

            orderLineItems.add(new OrderLineItem(orderLineItemCommand.getMenuId(), orderLineItemCommand.getQuantity(), orderLineItemPrice));
        }

        return new DeliveryOrder(DeliveryOrderStatus.WAITING, orderLineItems, command.getDeliveryAddress());
    }
}
