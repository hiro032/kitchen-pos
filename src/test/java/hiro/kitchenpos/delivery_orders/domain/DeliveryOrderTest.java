package hiro.kitchenpos.delivery_orders.domain;

import hiro.kitchenpos.delivery_orders.domain.exception.OrderStatusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeliveryOrderTest {

    @DisplayName("대기중인 배달 주문에게 접수 요청시 접수 상태로 변경 된다.")
    @Test
    void accept() {
        // Arrange
        OrderLineItem orderLineItem = new OrderLineItem();
        DeliveryOrder deliveryOrder = new DeliveryOrder(DeliveryOrderStatus.WAITING, Collections.singletonList(orderLineItem), "배달 주소");

        // Act
        deliveryOrder.accept();

        // Assert
        assertThat(deliveryOrder.getStatus()).isEqualTo(DeliveryOrderStatus.ACCEPTED);
    }

    @DisplayName("대기중인 상태가 아닌 배달 주문에게 접수 요청시 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource
    void accept_exception(final DeliveryOrderStatus status) {
        // Arrange
        OrderLineItem orderLineItem = new OrderLineItem();
        DeliveryOrder deliveryOrder = new DeliveryOrder(status, Collections.singletonList(orderLineItem), "배달 주소");

        // Act
        // Assert
        assertThatThrownBy(deliveryOrder::accept).isInstanceOf(OrderStatusException.class);
    }

    static Stream<Arguments> accept_exception() {
        return Stream.of(
                Arguments.of(
                        DeliveryOrderStatus.ACCEPTED
                ),
                Arguments.of(
                        DeliveryOrderStatus.COMPLETED
                ),
                Arguments.of(
                        DeliveryOrderStatus.DELIVERED
                ),
                Arguments.of(
                        DeliveryOrderStatus.SERVED
                ),
                Arguments.of(
                        DeliveryOrderStatus.DELIVERING
                )
        );
    }
}
