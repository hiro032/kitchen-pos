package hiro.kitchenpos.delivery_orders.domain;

import hiro.kitchenpos.delivery_orders.domain.exception.OrderStatusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeliveryOrderTest {

    @DisplayName("배달 상태 변경 성공 테스트")
    @Nested
    class ChangeStatusSuccessTest {
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

        @DisplayName("접수 상태인 배달 주문에게 서빙 요청시 서빙 상태로 변경 된다.")
        @Test
        void serve() {
            // Arrange
            OrderLineItem orderLineItem = new OrderLineItem();
            DeliveryOrder deliveryOrder = new DeliveryOrder(DeliveryOrderStatus.ACCEPTED, Collections.singletonList(orderLineItem), "배달 주소");

            // Act
            deliveryOrder.serve();

            // Assert
            assertThat(deliveryOrder.getStatus()).isEqualTo(DeliveryOrderStatus.SERVED);
        }


        @DisplayName("서빙 상태인 배달 주문에게 배달 시작 요청시 배달중 상태로 변경 된다.")
        @Test
        void start_delivery() {
            // Arrange
            OrderLineItem orderLineItem = new OrderLineItem();
            DeliveryOrder deliveryOrder = new DeliveryOrder(DeliveryOrderStatus.SERVED, Collections.singletonList(orderLineItem), "배달 주소");

            // Act
            deliveryOrder.startDelivery();

            // Assert
            assertThat(deliveryOrder.getStatus()).isEqualTo(DeliveryOrderStatus.DELIVERING);
        }
    }

    @DisplayName("배달 상태 변경 실패 테스트")
    @Nested
    class ChangeStatusExceptionTest {

        @DisplayName("대기중인 상태가 아닌 배달 주문에게 접수 요청시 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"ACCEPTED", "SERVED", "DELIVERING", "DELIVERED", "COMPLETED"})
        void accept_exception(final String status) {
            // Arrange
            OrderLineItem orderLineItem = new OrderLineItem();
            DeliveryOrder deliveryOrder = new DeliveryOrder(DeliveryOrderStatus.valueOf(status), Collections.singletonList(orderLineItem), "배달 주소");

            // Act
            // Assert
            assertThatThrownBy(deliveryOrder::accept)
                    .isInstanceOf(OrderStatusException.class);
        }

        @DisplayName("접수 상태가 아닌 배달 주문에게 서빙 요청시 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"WAITING", "SERVED", "DELIVERING", "DELIVERED", "COMPLETED"})
        void serve_exception(final String status) {
            // Arrange
            OrderLineItem orderLineItem = new OrderLineItem();
            DeliveryOrder deliveryOrder = new DeliveryOrder(DeliveryOrderStatus.valueOf(status), Collections.singletonList(orderLineItem), "배달 주소");

            // Act
            // Assert
            assertThatThrownBy(deliveryOrder::serve)
                    .isInstanceOf(OrderStatusException.class);
        }

        @DisplayName("서빙 완료 상태가 아닌 배달 주문에게 배달 시작 요청시 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"WAITING", "ACCEPTED", "DELIVERING", "DELIVERED", "COMPLETED"})
        void start_delivery_exception(final String status) {
            // Arrange
            OrderLineItem orderLineItem = new OrderLineItem();
            DeliveryOrder deliveryOrder = new DeliveryOrder(DeliveryOrderStatus.valueOf(status), Collections.singletonList(orderLineItem), "배달 주소");

            // Act
            // Assert
            assertThatThrownBy(deliveryOrder::startDelivery)
                    .isInstanceOf(OrderStatusException.class);
        }

    }

}
