package hiro.kitchenpos.menugroup.presentation;

import hiro.kitchenpos.acceptance.AcceptanceTest;
import hiro.kitchenpos.menugroup.presentation.dtos.ChangeMenuGroupRequest;
import hiro.kitchenpos.menugroup.presentation.dtos.CreateMenuGroupRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    @DisplayName("메뉴 그룹 이름을 null 이나 빈 값으로 생성시 에러가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void create_null_and_empty(final String name) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(new CreateMenuGroupRequest(name))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/menu-groups")
                .then().log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("메뉴 그룹 이름 변경시 변경된후 이름이 응답 된다.")
    @Test
    void change_menu_group_name() {
        String url = 메뉴_그룹_생성("한식");

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(new ChangeMenuGroupRequest("일식"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(url)
                .then().log().all()
                .extract();

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getUUID("id")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("name")).isEqualTo("일식")
        );
    }

    @DisplayName("메뉴 그룹 이름을 공백으로 변경시 에러가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void change_menu_group_name_null_and_empty(final String name) {
        String url = 메뉴_그룹_생성("한식");

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(new ChangeMenuGroupRequest(name))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(url)
                .then().log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    public static String 메뉴_그룹_생성(final String name) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(new CreateMenuGroupRequest(name))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/menu-groups")
                .then().log().all()
                .extract();

        return response.header("Location");
    }
}
