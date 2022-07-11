package hiro.kitchenpos.menu;

import hiro.kitchenpos.acceptance.AcceptanceTest;
import hiro.kitchenpos.menu.presentation.dtos.CreateMenuProductRequest;
import hiro.kitchenpos.menu.presentation.dtos.CreateMenuRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static hiro.kitchenpos.menu.MenuFixtures.createMenuProductRequest;
import static hiro.kitchenpos.menu.MenuFixtures.createMenuRequest;

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

    public static UUID 메뉴_생성(final UUID menuGroupId, UUID... productIds) {
        List<CreateMenuProductRequest> productRequests = Arrays.stream(productIds)
                .map(productId -> createMenuProductRequest(productId, 1))
                .collect(Collectors.toList());

        CreateMenuRequest request = createMenuRequest(menuGroupId, productRequests);

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
