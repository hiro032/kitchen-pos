package hiro.kitchenpos.menugroup.presentation;

import hiro.kitchenpos.acceptance.AcceptanceTest;
import hiro.kitchenpos.menugroup.presentation.dtos.CreateMenuGroupRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MenuGroupControllerTest extends AcceptanceTest {

    @DisplayName("메뉴 그룹 생성시 식별자를 포함한 url 이 응답된다.")
    @Test
    void create() {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(new CreateMenuGroupRequest("한식"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/menu-groups")
                .then().log().all()
                .extract();

        assertThat(response.header("Location")).isNotBlank();
    }
}
