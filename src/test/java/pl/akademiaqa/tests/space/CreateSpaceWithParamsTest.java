package pl.akademiaqa.tests.space;

import io.restassured.response.Response;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;

class CreateSpaceWithParamsTest {

    @ParameterizedTest(name = "Create space with space name: {0}")
    @DisplayName("Create space with space name")
    @MethodSource("createSpaceData")
    void createSpaceTests(String spaceName) {

        JSONObject space = new JSONObject();
        space.put("name", spaceName);

        final Response createResponse = CreateSpaceRequest.createSpace(space);

        Assertions.assertThat(createResponse.statusCode()).isEqualTo(200);
        Assertions.assertThat(createResponse.jsonPath().getString("name"))
            .isEqualTo(spaceName);

        final String spaceId = createResponse.jsonPath().getString("id");

        final Response deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(200);
    }

    private static Stream<Arguments> createSpaceData() {
        return Stream.of(
            Arguments.of("TEST 1"),
            Arguments.of("TEST 23"),
            Arguments.of("TEST 3*")
        );
    }
}
