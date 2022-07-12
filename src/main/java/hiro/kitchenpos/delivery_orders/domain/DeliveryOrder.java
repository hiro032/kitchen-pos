package hiro.kitchenpos.delivery_orders.domain;

import hiro.kitchenpos.delivery_orders.domain.exception.OrderStatusException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
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


    public DeliveryOrder(final DeliveryOrderStatus status, final List<OrderLineItem> orderLineItems, final String deliveryAddress) {
        this.id = UUID.randomUUID();
        this.status = status;
        this.orderLineItems = new OrderLineItems(orderLineItems);
        this.deliveryAddress = deliveryAddress;
        this.orderDateTime = LocalDateTime.now();
    }

    public void accept() {
        if (status != DeliveryOrderStatus.WAITING) {
            throw new OrderStatusException("대기중인 주문이 아니라면 접수가 불가능 합니다.");
        }
        this.status = DeliveryOrderStatus.ACCEPTED;
    }

    public void serve() {
        if (status != DeliveryOrderStatus.ACCEPTED) {
            throw new OrderStatusException("접수된 주문이 아니라면 서빙이 불가능 합니다.");
        }
        this.status = DeliveryOrderStatus.SERVED;
    }

    public void startDelivery() {
        if (status != DeliveryOrderStatus.SERVED) {
            throw new OrderStatusException("서빙이 완료된 주문이 아니라면 배달 시작이 불가능 합니다.");
        }
        this.status = DeliveryOrderStatus.DELIVERING;
    }

    public void completeDelivery() {
        if (status != DeliveryOrderStatus.DELIVERING) {
            throw new OrderStatusException("배달중인 주문이 아니라면 배달 완료가 불가능 합니다.");
        }
        this.status = DeliveryOrderStatus.DELIVERED;
    }

    public BigDecimal getOrderPrice() {
        return orderLineItems.totalPrice();
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems.getOrderLineItems();
    }
}
