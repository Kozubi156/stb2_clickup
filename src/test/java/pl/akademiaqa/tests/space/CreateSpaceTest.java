package pl.akademiaqa.tests.space;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;

class CreateSpaceTest {

    private static final String SPACE_NAME = "MY SPACE FROM JAVA";

    @Test
    void createSpaceTests() {
        String spaceId = createSpaceStep();
        deleteSpaceStep(spaceId);
    }

    private String createSpaceStep() {
        JSONObject space = new JSONObject();
        space.put("name", SPACE_NAME);

        final Response createResponse = CreateSpaceRequest.createSpace(space);

        Assertions.assertThat(createResponse.statusCode()).isEqualTo(200);
        Assertions.assertThat(createResponse.jsonPath().getString("name"))
            .isEqualTo(SPACE_NAME);

        return createResponse.jsonPath().getString("id");
    }

    private void deleteSpaceStep(String spaceId) {
        final Response deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }
}
