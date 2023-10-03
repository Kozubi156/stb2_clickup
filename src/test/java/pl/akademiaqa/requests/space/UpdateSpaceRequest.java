package pl.akademiaqa.requests.space;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.dto.space.request.UpdateSpaceRequestDto;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.ClickUpUrl;

public class UpdateSpaceRequest {

    public static UpdateSpaceRequestDto updateSpace(UpdateSpaceRequestDto spaceDto,
        String spaceId) {

        return given()
            .spec(BaseRequest.requestSpecWithLogs())
            .body(spaceDto)
            .when()
            .put(ClickUpUrl.getSpaceUrl(spaceId))
            .then()
            .statusCode(200)
            .extract()
            .as(UpdateSpaceRequestDto.class);
    }

    public static Response updateSpace(JSONObject jsonObject, String spaceId) {

        return given()
            .spec(BaseRequest.requestSpecWithLogs())
            .body(jsonObject.toString())
            .when()
            .put(ClickUpUrl.getSpaceUrl(spaceId))
            .then()
            .extract()
            .response();
    }
}
