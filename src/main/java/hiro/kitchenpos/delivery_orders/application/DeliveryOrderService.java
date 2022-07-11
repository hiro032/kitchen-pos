package hiro.kitchenpos.delivery_orders.application;

import hiro.kitchenpos.delivery_orders.application.dtos.CreateDeliveryOrderCommand;
import hiro.kitchenpos.delivery_orders.application.dtos.DeliveryOrderInfo.*;
import hiro.kitchenpos.delivery_orders.application.dtos.OrderLineItemCommand;
import hiro.kitchenpos.delivery_orders.application.dtos.OrderLineItemInfo.*;
import hiro.kitchenpos.delivery_orders.domain.DeliveryOrder;
import hiro.kitchenpos.delivery_orders.domain.DeliveryOrderRepository;
import hiro.kitchenpos.delivery_orders.domain.DeliveryOrderStatus;
import hiro.kitchenpos.delivery_orders.domain.OrderLineItem;
import hiro.kitchenpos.delivery_orders.domain.exception.DeliveryOrderNotFoundException;
import hiro.kitchenpos.menu.domain.MenuRepository;
import hiro.kitchenpos.menu.domain.exception.MenuNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class DeliveryOrderService {

    private final DeliveryOrderRepository deliveryOrderRepository;
    private final MenuRepository menuRepository;

    public CreateDeliveryOrderInfo create(final CreateDeliveryOrderCommand command) {
        validateMenuIds(command.getOrderLineItemCommands());

        List<OrderLineItem> orderLineItems = command.getOrderLineItemCommands().stream()
                .map(orderLineItemCommand
                        -> new OrderLineItem(orderLineItemCommand.getMenuId(), orderLineItemCommand.getQuantity()))
                .collect(Collectors.toList());

        DeliveryOrder deliveryOrder = new DeliveryOrder(DeliveryOrderStatus.WAITING, orderLineItems, command.getDeliveryAddress());

        DeliveryOrder entity = deliveryOrderRepository.save(deliveryOrder);

        return CreateDeliveryOrderInfo.builder()
                .id(entity.getId())
                .orderStatus(entity.getStatus().name())
                .deliveryAddress(entity.getDeliveryAddress())
                .orderDateTime(entity.getOrderDateTime())
                .orderLineItemsInfo(entity.getOrderLineItems().stream()
                        .map(CreateOrderLineItemInfo::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    // 주문한 menu 의 식별자를 통해 존재하는 메뉴인지 확인.
    private void validateMenuIds(final List<OrderLineItemCommand> orderLineItemCommands) {
        List<UUID> menuIds = orderLineItemCommands.stream()
                .map(OrderLineItemCommand::getMenuId)
                .collect(Collectors.toList());

        long count = menuIds.stream()
                .filter(menuRepository::existsById)
                .count();

        if (menuIds.size() != count) {
            throw new MenuNotFoundException();
        }
    }

    public void accept(final UUID id) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(id)
                .orElseThrow(DeliveryOrderNotFoundException::new);

        deliveryOrder.accept();
    }
}
