package hiro.kitchenpos.delivery_orders.presentation;

import hiro.kitchenpos.delivery_orders.application.DeliveryOrderService;
import hiro.kitchenpos.delivery_orders.application.dtos.DeliveryOrderInfo.*;
import hiro.kitchenpos.delivery_orders.presentation.dtos.DeliveryOrderResponse.CreateDeliveryOrderResponse;
import hiro.kitchenpos.delivery_orders.presentation.dtos.DeliveryOrdersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

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

}
