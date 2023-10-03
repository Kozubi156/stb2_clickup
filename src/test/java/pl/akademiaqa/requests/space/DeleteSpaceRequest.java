package pl.akademiaqa.requests.space;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.properties.ClickUpProperties;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.ClickUpUrl;

public class DeleteSpaceRequest {

    public static Response deleteSpace(String spaceId) {

        return given()
            .spec(BaseRequest.requestSpecWithLogs())
            .when()
            .delete(ClickUpUrl.getSpaceUrl(spaceId))
            .then()
            .extract()
            .response();
    }
}
