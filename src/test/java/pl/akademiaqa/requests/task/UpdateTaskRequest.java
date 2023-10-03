package pl.akademiaqa.requests.task;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.ClickUpUrl;

public class UpdateTaskRequest {

    public static Response updateTask(JSONObject updateTask, String taskId) {

        return given()
            .spec(BaseRequest.requestSpecWithLogs())
            .body(updateTask.toString())
            .when()
            .put(ClickUpUrl.getTaskUrl(taskId))
            .then()
            .statusCode(200)
            .extract()
            .response();
    }
}
