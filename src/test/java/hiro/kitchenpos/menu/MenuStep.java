package hiro.kitchenpos.menu;

import hiro.kitchenpos.acceptance.AcceptanceTest;
import hiro.kitchenpos.menu.presentation.dtos.CreateMenuRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.UUID;

public class MenuStep extends AcceptanceTest {

    public static final String MENU_END_POINT = "/api/menus";

    public static UUID 메뉴_생성(final CreateMenuRequest request) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(MENU_END_POINT)
                .then().log().all()
                .extract();

        return response.jsonPath().getUUID("id");
    }
}
