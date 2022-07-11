package hiro.kitchenpos.delivery_orders;

import hiro.kitchenpos.delivery_orders.presentation.dtos.DeliveryOrdersRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.UUID;
import org.springframework.http.MediaType;

public class DeliveryOrdersStep {

    private static final String END_POINT = "/api/delivery-orders";

    public static ExtractableResponse<Response> 배달_주문_생성(final DeliveryOrdersRequest request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(END_POINT)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 배달_주문_접수(final UUID orderId) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .patch(END_POINT + "/" + orderId + "/accept")
                .then().log().all()
                .extract();
    }

}
