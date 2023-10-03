package pl.akademiaqa.tests.space;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.dto.space.request.UpdateSpaceRequestDto;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;
import pl.akademiaqa.requests.space.UpdateSpaceRequest;

class UpdateSpaceTest {

    private static final String SPACE_NAME = "MY SPACE FROM JAVA";
    private static final String EDITED_SPACE_NAME = "NEW SPACE NAME";
    private String spaceId;

    @Test
    void updateSpaceTests() {
        spaceId = createSpaceStep();
        updateSpaceStep();
        deleteSpaceStep();
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

    private void deleteSpaceStep() {
        final Response deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }

    private void updateSpaceStep() {
        UpdateSpaceRequestDto updateSpaceDto = new UpdateSpaceRequestDto();
        updateSpaceDto.setName(EDITED_SPACE_NAME);

        final var updateSpaceResponse = UpdateSpaceRequest.updateSpace(updateSpaceDto,
            spaceId);
        Assertions.assertThat(updateSpaceResponse.getName()).isEqualTo(EDITED_SPACE_NAME);
    }
}
