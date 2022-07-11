package hiro.kitchenpos.delivery_orders.presentation;

import static hiro.kitchenpos.delivery_orders.DeliveryOrderFixtures.deliveryOrdersRequest;
import static hiro.kitchenpos.delivery_orders.DeliveryOrderFixtures.orderLineItemRequest;
import static hiro.kitchenpos.delivery_orders.DeliveryOrdersStep.배달_주문_생성;
import static hiro.kitchenpos.delivery_orders.DeliveryOrdersStep.배달_주문_접수;
import static hiro.kitchenpos.menu.MenuStep.메뉴_생성;
import static hiro.kitchenpos.menugroup.step.MenuGroupStep.메뉴_그룹_생성;
import static hiro.kitchenpos.product.ProductsFixtures.createProductRequest;
import static hiro.kitchenpos.product.step.ProductStep.상품_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import hiro.kitchenpos.acceptance.AcceptanceTest;
import hiro.kitchenpos.delivery_orders.domain.DeliveryOrderStatus;
import hiro.kitchenpos.delivery_orders.presentation.dtos.DeliveryOrdersRequest;
import hiro.kitchenpos.delivery_orders.presentation.dtos.OrderLineItemRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class DeliveryOrdersControllerTest extends AcceptanceTest {

    private static final String END_POINT = "/api/delivery-orders";

    /**
     *  Arrange
     *  메뉴가 등록 되어 있음.
     *
     *  Act
     *  메뉴에 대한 배달 주문 요청
     *
     *  Assert
     *  배달 주문이 생성됨 & 배달 주문 정보 응답.
     */
    @DisplayName("배달 주문 요청 테스트")
    @Test
    void create_delivery_order() {
        // Arrange
        UUID 치킨_콜라 = 메뉴_그룹_생성("치킨 + 콜라");
        UUID 치킨 = 상품_생성(createProductRequest("치킨"));
        UUID 콜라 = 상품_생성(createProductRequest("콜라"));

        UUID menuId = 메뉴_생성(치킨_콜라, 치킨, 콜라);

        // Act
        OrderLineItemRequest orderLineItemRequest = orderLineItemRequest(menuId, 1);
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(deliveryOrdersRequest(orderLineItemRequest))
                .when()
                .post(END_POINT)
                .then().log().all()
                .extract();

        // Assert
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(response.jsonPath().getUUID("id")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("orderStatus")).isEqualTo(DeliveryOrderStatus.WAITING.name()),
                () -> assertThat(response.jsonPath().getUUID("orderLineItemsInfo[0].menuId")).isEqualTo(menuId)
        );
    }

    /**
     *  Arrange
     *  메뉴가 등록 되어 있음.
     *  메뉴에 대한 배달 주문 요청
     *
     *  Act
     *  배달 주문에 대한 접수 요청
     *
     *  Assert
     *  배달 주문이 접수 된다.
     */
    @DisplayName("배달 주문 접수 테스트")
    @Test
    void delivery_order_accept() {
        // Arrange
        UUID 치킨_콜라 = 메뉴_그룹_생성("치킨 + 콜라");
        UUID 치킨 = 상품_생성(createProductRequest("치킨"));
        UUID 콜라 = 상품_생성(createProductRequest("콜라"));

        UUID menuId = 메뉴_생성(치킨_콜라, 치킨, 콜라);

        OrderLineItemRequest orderLineItemRequest = orderLineItemRequest(menuId, 1);
        DeliveryOrdersRequest deliveryOrdersRequest = deliveryOrdersRequest(orderLineItemRequest);

        UUID orderId = 배달_주문_생성(deliveryOrdersRequest).jsonPath().getUUID("id");

        // Act
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .patch(END_POINT + "/" + orderId + "/accept")
                .then().log().all()
                .extract();

        // Assert
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getUUID("id")).isEqualTo(orderId),
                () -> assertThat(response.jsonPath().getString("orderStatus")).isEqualTo(DeliveryOrderStatus.ACCEPTED.name())
        );
    }

    /**
     *  Arrange
     *  메뉴가 등록 되어 있음.
     *  메뉴에 대한 배달 주문 요청
     *  배달 주문에 대한 접수 요청
     *
     *  Act
     *  배달 주문에 대한 서빙 요청
     *
     *  Assert
     *  배달 주문이 서빙 된다빙
     */
    @DisplayName("배달 주문 서빙 테스트")
    @Test
    void delivery_order_served() {
        // Arrange
        UUID 치킨_콜라 = 메뉴_그룹_생성("치킨 + 콜라");
        UUID 치킨 = 상품_생성(createProductRequest("치킨"));
        UUID 콜라 = 상품_생성(createProductRequest("콜라"));

        UUID menuId = 메뉴_생성(치킨_콜라, 치킨, 콜라);

        OrderLineItemRequest orderLineItemRequest = orderLineItemRequest(menuId, 1);
        DeliveryOrdersRequest deliveryOrdersRequest = deliveryOrdersRequest(orderLineItemRequest);

        UUID orderId = 배달_주문_생성(deliveryOrdersRequest).jsonPath().getUUID("id");
        배달_주문_접수(orderId);

        // Act
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .patch(END_POINT + "/" + orderId + "/serve")
                .then().log().all()
                .extract();

        // Assert
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getUUID("id")).isEqualTo(orderId),
                () -> assertThat(response.jsonPath().getString("orderStatus")).isEqualTo(DeliveryOrderStatus.SERVED.name())
        );
    }

}
