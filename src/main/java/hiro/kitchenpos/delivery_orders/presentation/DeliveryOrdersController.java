package hiro.kitchenpos.delivery_orders.presentation;

import hiro.kitchenpos.delivery_orders.application.DeliveryOrderService;
import hiro.kitchenpos.delivery_orders.application.dtos.DeliveryOrderInfo.ChangeStatusDeliveryOrderInfo;
import hiro.kitchenpos.delivery_orders.application.dtos.DeliveryOrderInfo.CreateDeliveryOrderInfo;
import hiro.kitchenpos.delivery_orders.presentation.dtos.DeliveryOrderResponse.ChangeStatusDeliveryOrderResponse;
import hiro.kitchenpos.delivery_orders.presentation.dtos.DeliveryOrderResponse.CreateDeliveryOrderResponse;
import hiro.kitchenpos.delivery_orders.presentation.dtos.DeliveryOrdersRequest;
import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/delivery-orders")
@RestController
public class DeliveryOrdersController {

    private final DeliveryOrderService deliveryOrderService;

    @PostMapping
    public ResponseEntity<CreateDeliveryOrderResponse> create(@Valid @RequestBody DeliveryOrdersRequest request) {
        CreateDeliveryOrderInfo info = deliveryOrderService.create(request.toCommand());

        CreateDeliveryOrderResponse response = CreateDeliveryOrderResponse.builder()
                .id(info.getId())
                .orderStatus(info.getOrderStatus())
                .deliveryAddress(info.getDeliveryAddress())
                .orderDateTime(info.getOrderDateTime())
                .orderLineItemsInfo(info.getOrderLineItemsInfo())
                .build();

        return ResponseEntity.created(URI.create("/api/delivery-orders/" + response.getId()))
                .body(response);
    }

    @PatchMapping("/{orderId}/accept")
    public ResponseEntity<ChangeStatusDeliveryOrderResponse> accept(@PathVariable UUID orderId) {
        ChangeStatusDeliveryOrderInfo info = deliveryOrderService.accept(orderId);

        ChangeStatusDeliveryOrderResponse response = ChangeStatusDeliveryOrderResponse.builder()
                .id(info.getId())
                .orderStatus(info.getOrderStatus())
                .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{orderId}/serve")
    public ResponseEntity<Void> serve(@PathVariable UUID orderId) {
        deliveryOrderService.serve(orderId);

        return ResponseEntity.ok().build();
    }

}
