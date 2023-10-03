package pl.akademiaqa.tests.space;

import io.restassured.response.Response;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.akademiaqa.dto.space.request.UpdateSpaceRequestDto;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;
import pl.akademiaqa.requests.space.UpdateSpaceRequest;

class UpdateSpaceWithParamsTest {

    private static final String SPACE_NAME = "MY SPACE FROM JAVA";
    private String spaceId;

    @ParameterizedTest(name = "Update space with space name: {0}")
    @DisplayName("Update space with space name")
    @MethodSource("updateSpaceData")
    void updateSpaceTests(String spaceName) {
        spaceId = createSpaceStep();
        updateSpaceStep(spaceName);
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

    private void updateSpaceStep(String spaceName) {
        UpdateSpaceRequestDto updateSpaceDto = new UpdateSpaceRequestDto();
        updateSpaceDto.setName(spaceName);

        final var updateSpaceResponse = UpdateSpaceRequest.updateSpace(updateSpaceDto,
            spaceId);
        Assertions.assertThat(updateSpaceResponse.getName()).isEqualTo(spaceName);
    }

    private static Stream<Arguments> updateSpaceData() {
        return Stream.of(
            Arguments.of("SPACE TEST 1"),
            Arguments.of("SPACE TEST 23"),
            Arguments.of("SPACE TEST 3*")
        );
    }
}
