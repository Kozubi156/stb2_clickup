package pl.akademiaqa.requests.space;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.properties.ClickUpProperties;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.ClickUpUrl;

public class CreateSpaceRequest {

    public static Response createSpace(JSONObject space) {

        return given()
            .spec(BaseRequest.requestSpecWithLogs())
            .body(space.toString())
            .when()
            .post(ClickUpUrl.getSpacesUrl(ClickUpProperties.getTeamId()))
            .then()
            .extract()
            .response();
    }
}
