package pl.akademiaqa.requests.list;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.ClickUpUrl;

public class CreateListRequest {

    public static Response createList(JSONObject list, String spaceId) {

        return given()
            .spec(BaseRequest.requestSpecWithLogs())
            .body(list.toString())
            .when()
            .post(ClickUpUrl.getListsUrl(spaceId))
            .then()
            .extract()
            .response();
    }
}
