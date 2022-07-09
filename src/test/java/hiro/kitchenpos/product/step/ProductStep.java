package hiro.kitchenpos.product.step;

import hiro.kitchenpos.acceptance.AcceptanceTest;
import hiro.kitchenpos.product.presentation.dtos.CreateProductRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.UUID;

public class ProductStep extends AcceptanceTest {

    private static final String END_POINT = "/api/products";

    public static UUID 상품_생성(final CreateProductRequest request) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(END_POINT)
                .then().log().all()
                .extract();

        return response.jsonPath().getUUID("id");
    }
}
