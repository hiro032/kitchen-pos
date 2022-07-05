package hiro.kitchenpos.product.presentation;

import hiro.kitchenpos.acceptance.AcceptanceTest;
import hiro.kitchenpos.product.presentation.dtos.ChangeProductRequest;
import hiro.kitchenpos.product.presentation.dtos.CreateProductRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProductControllerTest extends AcceptanceTest {

    private static final String END_POINT = "/api/products";

    @DisplayName("상품 이름과 가격을 통해 생성에 성공하면 식별자가 포함된 url 이 응답 된다.")
    @Test
    void create() {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(new CreateProductRequest("치킨", BigDecimal.TEN))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(END_POINT)
                .then().log().all()
                .extract();

        assertAll(
                () -> assertThat(response.headers().get("Location")).isNotNull(),
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
        );
    }

    @DisplayName("상품 생성시 상품 이름이름을 입력하지 않은 경우, 에러가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void empty_product_name(final String name) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(new CreateProductRequest(name, BigDecimal.TEN))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(END_POINT)
                .then().log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("상품 생성시 상품 가격이 0보다 작을 경우, 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(longs = {-1000, -1})
    void product_price_less_than_zero(final long price) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(new CreateProductRequest("치킨", BigDecimal.valueOf(price)))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(END_POINT)
                .then().log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Arrange 상품이 존재한다.
     * Act 정보를 변경할 상품의 식별자와 상품의 변경 정보를 통해 변경 요청을 한다.
     * Assert 상품정보가 변경 된다.
     */
    @DisplayName("상품 정보 변경시 변경된 상품 정보가 응답 된다.")
    @Test
    void change_product_info() {
        String url = 상품_생성_요청();

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(new ChangeProductRequest("뉴 치킨", BigDecimal.valueOf(10000)))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(url)
                .then().log().all()
                .extract();

        assertAll(
                () -> assertThat(response.jsonPath().getUUID("id")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("name")).isEqualTo("뉴 치킨"),
                () -> assertThat(response.jsonPath().getLong("price")).isEqualTo(BigDecimal.valueOf(10000).longValue())
        );
    }

    @DisplayName("수정할 상품 이름이 null 이거나 빈 값인 경우 에러가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void change_product_name_null_or_empty(final String name) {
        String url = 상품_생성_요청();

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(new ChangeProductRequest(name, BigDecimal.valueOf(10000)))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(url)
                .then().log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("수정할 상품 가격이 0 보다 작은 경우 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(longs = {-1000, -1})
    void change_product_price_less_than_zero(final long price) {

        String url = 상품_생성_요청();

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(new ChangeProductRequest("뉴 치킨", BigDecimal.valueOf(price)))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(url)
                .then().log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private static String 상품_생성_요청() {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(new CreateProductRequest("치킨", BigDecimal.valueOf(20000)))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post(END_POINT)
                .then().log().all()
                .extract();

        return response.header("Location");
    }
}
