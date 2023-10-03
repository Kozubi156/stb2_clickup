package pl.akademiaqa.requests.task;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.akademiaqa.dto.task.request.CreateTaskRequestDto;
import pl.akademiaqa.dto.task.response.CreateTaskResponseDto;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.ClickUpUrl;

public class CreateTaskRequest {

    public static Response createTask(JSONObject task, String listId) {

        return given()
            .spec(BaseRequest.requestSpecWithLogs())
            .body(task.toString())
            .when()
            .post(ClickUpUrl.getTasksUrl(listId))
            .then()
            .extract()
            .response();
    }

    public static CreateTaskResponseDto createTask(CreateTaskRequestDto taskDto, String listId) {

        return given()
            .spec(BaseRequest.requestSpecWithLogs())
            .body(taskDto)
            .when()
            .post(ClickUpUrl.getTasksUrl(listId))
            .then()
            .statusCode(200)
            .extract()
            .response()
            .as(CreateTaskResponseDto.class);
    }
}
