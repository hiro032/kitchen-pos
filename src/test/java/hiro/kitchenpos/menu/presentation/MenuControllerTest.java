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

import java.util.Arrays;
import java.util.UUID;

import static hiro.kitchenpos.menu.MenuFixtures.createMenuProductRequest;
import static hiro.kitchenpos.menu.MenuFixtures.createMenuRequest;
import static hiro.kitchenpos.menu.MenuStep.MENU_END_POINT;
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

        CreateMenuRequest menuRequest = createMenuRequest(menuGroupId, Arrays.asList(createMenuProductRequest(치킨, 1), createMenuProductRequest(콜라, 2)));

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
}
