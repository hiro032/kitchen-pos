package hiro.kitchenpos.menugroup.step;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.UUID;

import static hiro.kitchenpos.menugroup.MenuGroupFixtures.createMenuGroupRequest;

public class MenuGroupStep {

    private static final String END_POINT = "/api/menu-groups";

    public static UUID 메뉴_그룹_생성() {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createMenuGroupRequest())
                .when()
                .post(END_POINT)
                .then().log().all()
                .extract();

        return response.jsonPath().getUUID("id");
    }

    public static UUID 메뉴_그룹_생성(final String name) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createMenuGroupRequest(name))
                .when()
                .post(END_POINT)
                .then().log().all()
                .extract();

        return response.jsonPath().getUUID("id");
    }
}
