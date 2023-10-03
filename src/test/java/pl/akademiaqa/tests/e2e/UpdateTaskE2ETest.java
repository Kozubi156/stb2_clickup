package pl.akademiaqa.tests.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.akademiaqa.dto.task.request.CreateTaskRequestDto;
import pl.akademiaqa.requests.list.CreateListRequest;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;
import pl.akademiaqa.requests.task.CreateTaskRequest;
import pl.akademiaqa.requests.task.UpdateTaskRequest;

class UpdateTaskE2ETest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2ETest.class);
    private static final String SPACE_NAME = "SPACE E2E";
    private static final String LIST_NAME = "ZADANIA";
    private static final String TASK_NAME = "Przetestowac cos";
    private String spaceId;
    private String listId;
    private String taskId;

    @Test
    void updateTaskE2ETest() {
        spaceId = createSpaceStep();
        LOGGER.info("Space created with id: {}", spaceId);

        listId = createListStep();
        LOGGER.info("Space created with id: {}", listId);

        taskId = createTaskStep();
        LOGGER.info("Task created with id: {}", taskId);

        updateTaskStep();
        closeTaskStep();
        deleteSpaceStep();
    }

    private String createSpaceStep() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", SPACE_NAME);

        final var responseSpace = CreateSpaceRequest.createSpace(jsonObject);
        Assertions.assertThat(responseSpace.statusCode()).isEqualTo(200);

        JsonPath jsonData = responseSpace.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(SPACE_NAME);

        return jsonData.getString("id");
    }

    private String createListStep() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", LIST_NAME);

        final var responseList = CreateListRequest.createList(jsonObject, spaceId);
        Assertions.assertThat(responseList.statusCode()).isEqualTo(200);

        JsonPath jsonData = responseList.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(LIST_NAME);

        return jsonData.getString("id");
    }

    private String createTaskStep() {
        CreateTaskRequestDto taskDto = new CreateTaskRequestDto();
        taskDto.setName(TASK_NAME);
        taskDto.setDescription("Ciekawe jak to działa");
        taskDto.setStatus("to do");

        final var responseTask = CreateTaskRequest.createTask(taskDto, listId);

        Assertions.assertThat(responseTask.getName()).isEqualTo(TASK_NAME);
        Assertions.assertThat(responseTask.getDescription()).isEqualTo("Ciekawe jak to działa");

        return responseTask.getId();
    }

    private void updateTaskStep() {
        JSONObject updateTask = new JSONObject();
        updateTask.put("name", "Nowa nazwa zadania");
        updateTask.put("description", "Może nie działa to już");

        final var responseTask = UpdateTaskRequest.updateTask(updateTask, taskId);
        Assertions.assertThat(responseTask.statusCode()).isEqualTo(200);

        JsonPath jsonData = responseTask.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo("Nowa nazwa zadania");
        Assertions.assertThat(jsonData.getString("description"))
            .isEqualTo("Może nie działa to już");

    }

    private void closeTaskStep() {
        JSONObject updateTask = new JSONObject();
        updateTask.put("status", "complete");

        final var responseTask = UpdateTaskRequest.updateTask(updateTask, taskId);
        Assertions.assertThat(responseTask.statusCode()).isEqualTo(200);

        JsonPath jsonData = responseTask.jsonPath();
        Assertions.assertThat(jsonData.getString("status.status")).isEqualTo("complete");
    }

    private void deleteSpaceStep() {
        Response deleteSpaceResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteSpaceResponse.statusCode()).isEqualTo(200);
    }
}
