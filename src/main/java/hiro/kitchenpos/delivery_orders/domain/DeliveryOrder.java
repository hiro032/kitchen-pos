package hiro.kitchenpos.delivery_orders.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class DeliveryOrder {

    @Column(name = "id", columnDefinition = "varbinary(16)")
    @Id
    private UUID id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryOrderStatus status;

    @Embedded
    private OrderLineItems orderLineItems;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "order_date_time")
    private LocalDateTime orderDateTime;


    public DeliveryOrder(DeliveryOrderStatus status, List<OrderLineItem> orderLineItems, String deliveryAddress) {
        this.id = UUID.randomUUID();
        this.status = status;
        this.orderLineItems = new OrderLineItems(orderLineItems);
        this.deliveryAddress = deliveryAddress;
        this.orderDateTime = LocalDateTime.now();
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems.getOrderLineItems();
    }
}
