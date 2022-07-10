package hiro.kitchenpos.menu.presentation;

import hiro.kitchenpos.acceptance.AcceptanceTest;
import hiro.kitchenpos.menu.presentation.dtos.CreateMenuRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static hiro.kitchenpos.menu.MenuFixtures.*;
import static hiro.kitchenpos.menu.MenuStep.MENU_END_POINT;
import static hiro.kitchenpos.menu.MenuStep.메뉴_생성;
import static hiro.kitchenpos.menugroup.step.MenuGroupStep.메뉴_그룹_생성;
import static hiro.kitchenpos.product.ProductsFixtures.createProductRequest;
import static hiro.kitchenpos.product.step.ProductStep.상품_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MenuControllerTest extends AcceptanceTest {

    /**
     * Arrange
     * 메뉴 그룹 생성
     * 상품 생성
     *
     * Act
     * 메뉴 생성
     *
     * Assert
     * 식별자 응답.
     */
    @DisplayName("메뉴를 생성한다.")
    @Test
    void create() {
        UUID menuGroupId = 메뉴_그룹_생성();
        UUID 치킨 = 상품_생성(createProductRequest("치킨"));
        UUID 콜라 = 상품_생성(createProductRequest("콜라"));

        CreateMenuRequest menuRequest = createMenuRequest(
                menuGroupId
                , Arrays.asList(
                        createMenuProductRequest(치킨, 1)
                        , createMenuProductRequest(콜라, 2)));

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(menuRequest)
                .when()
                .post(MENU_END_POINT)
                .then().log().all()
                .extract();

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(response.jsonPath().getUUID("id")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("name")).isEqualTo(menuRequest.getName()),
                () -> assertThat(response.jsonPath().getDouble("price")).isEqualTo(menuRequest.getPrice().doubleValue())
        );
    }

    /**
     * Arrange
     * 메뉴 그룹 생성
     * 상품 생성
     * 메뉴 생성
     *
     * Act
     * 메뉴 가격 변경
     *
     * Assert
     * 변경된 메뉴의 정보 응답.
     */
    @DisplayName("메뉴의 가격을 변경한다.")
    @Test
    void change_price() {
        // Arrange
        UUID menuGroupId = 메뉴_그룹_생성();
        UUID 치킨 = 상품_생성(createProductRequest("치킨"));
        UUID 콜라 = 상품_생성(createProductRequest("콜라"));

        CreateMenuRequest menuRequest = createMenuRequest(
                menuGroupId
                , Arrays.asList(
                        createMenuProductRequest(치킨, 1),
                        createMenuProductRequest(콜라, 1)
                )
        );

        UUID menuId = 메뉴_생성(menuRequest);

        // Act
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(changeMenuPriceRequest(BigDecimal.valueOf(5000)))
                .when()
                .put(MENU_END_POINT + "/" + menuId + "/price")
                .then().log().all()
                .extract();

        // Assert
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getDouble("price")).isEqualTo(5000)
        );
    }

    /**
     * Arrange
     * 메뉴를 등록한다.
     *
     * Act
     * 메뉴의 전시 상태를 숨김으로 변경한다.
     *
     * Assert
     * 메뉴의 전시 상태가 숨김으로 변경된다.
     */
    @DisplayName("메뉴의 전시 상태를 숨김으로 변경한다.")
    @Test
    void menu_hide() {
        // Arrange
        UUID menuGroupId = 메뉴_그룹_생성();
        UUID 치킨 = 상품_생성(createProductRequest("치킨"));
        UUID 콜라 = 상품_생성(createProductRequest("콜라"));

        CreateMenuRequest menuRequest = createMenuRequest(
                menuGroupId
                , Arrays.asList(
                        createMenuProductRequest(치킨, 1),
                        createMenuProductRequest(콜라, 1)
                )
        );

        UUID menuId = 메뉴_생성(menuRequest);

        // Act
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .patch(MENU_END_POINT + "/" + menuId + "/hide")
                .then().log().all()
                .extract();

        // Assert
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getUUID("id")).isEqualTo(menuId),
                () -> assertThat(response.jsonPath().getBoolean("displayed")).isFalse()
        );
    }

    /**
     * Arrange
     * 메뉴를 등록한다
     *
     * Act
     * 메뉴의 전시 상태를 전시중 으로 변경한다.
     *
     * Assert
     * 메뉴의 전시 상태가 전시중으로 변경 된다.
     */
    @DisplayName("메뉴의 전시 상태를 전시중 으로 변경한다.")
    @Test
    void menu_display() {
        // Arrange
        UUID menuGroupId = 메뉴_그룹_생성();
        UUID 치킨 = 상품_생성(createProductRequest("치킨"));
        UUID 콜라 = 상품_생성(createProductRequest("콜라"));

        CreateMenuRequest menuRequest = createMenuRequest(
                menuGroupId
                , Arrays.asList(
                        createMenuProductRequest(치킨, 1),
                        createMenuProductRequest(콜라, 1)
                )
        );

        UUID menuId = 메뉴_생성(menuRequest);

        // Act
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .patch(MENU_END_POINT + "/" + menuId + "/display")
                .then().log().all()
                .extract();

        // Assert
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getUUID("id")).isEqualTo(menuId),
                () -> assertThat(response.jsonPath().getBoolean("displayed")).isTrue()
        );
    }
}
