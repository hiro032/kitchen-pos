package hiro.kitchenpos.delivery_orders.application;

import hiro.kitchenpos.delivery_orders.application.dtos.CreateDeliveryOrderCommand;
import hiro.kitchenpos.delivery_orders.application.dtos.DeliveryOrderInfo.ChangeStatusDeliveryOrderInfo;
import hiro.kitchenpos.delivery_orders.application.dtos.DeliveryOrderInfo.CreateDeliveryOrderInfo;
import hiro.kitchenpos.delivery_orders.application.dtos.OrderLineItemInfo.CreateOrderLineItemInfo;
import hiro.kitchenpos.delivery_orders.domain.DeliveryOrder;
import hiro.kitchenpos.delivery_orders.domain.DeliveryOrderRepository;
import hiro.kitchenpos.delivery_orders.domain.RidersClient;
import hiro.kitchenpos.delivery_orders.domain.exception.DeliveryOrderNotFoundException;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeliveryOrderService {

    private final DeliveryOrderRepository deliveryOrderRepository;

    private final DeliveryOrderServiceValidator deliveryOrderServiceValidator;
    private final DeliveryOrderFactory deliveryOrderFactory;
    private final RidersClient ridersClient;

    public CreateDeliveryOrderInfo create(final CreateDeliveryOrderCommand command) {
        deliveryOrderServiceValidator.validateMenuIds(command.getOrderLineItemCommands());

        DeliveryOrder deliveryOrder = deliveryOrderFactory.createDeliveryOrder(command);

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

    public ChangeStatusDeliveryOrderInfo accept(final UUID id) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(id)
                .orElseThrow(DeliveryOrderNotFoundException::new);

        deliveryOrder.accept();

        return ChangeStatusDeliveryOrderInfo.builder()
                .id(deliveryOrder.getId())
                .orderStatus(deliveryOrder.getStatus().name())
                .build();
    }

    public ChangeStatusDeliveryOrderInfo serve(final UUID id) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(id)
                .orElseThrow(DeliveryOrderNotFoundException::new);

        deliveryOrder.serve();

        return ChangeStatusDeliveryOrderInfo.builder()
                .id(deliveryOrder.getId())
                .orderStatus(deliveryOrder.getStatus().name())
                .build();
    }

    public ChangeStatusDeliveryOrderInfo startDelivery(final UUID id) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(id)
                .orElseThrow(DeliveryOrderNotFoundException::new);

        deliveryOrder.startDelivery();

        ridersClient.requestDelivery(id, deliveryOrder.getOrderPrice(), deliveryOrder.getDeliveryAddress());

        return ChangeStatusDeliveryOrderInfo.builder()
                .id(deliveryOrder.getId())
                .orderStatus(deliveryOrder.getStatus().name())
                .build();
    }

    public ChangeStatusDeliveryOrderInfo completeDelivery(final UUID id) {
        DeliveryOrder deliveryOrder = deliveryOrderRepository.findById(id)
                .orElseThrow(DeliveryOrderNotFoundException::new);

        deliveryOrder.completeDelivery();

        return ChangeStatusDeliveryOrderInfo.builder()
                .id(deliveryOrder.getId())
                .orderStatus(deliveryOrder.getStatus().name())
                .build();
    }
}
