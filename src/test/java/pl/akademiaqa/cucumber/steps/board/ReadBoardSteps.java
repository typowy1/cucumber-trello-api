package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java8.En;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import pl.akademiaqa.api.trello.ReadRequest;
import pl.akademiaqa.commom.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

import static org.assertj.core.api.Assertions.*;

public class ReadBoardSteps implements En {

    private final RequestHandler requestHandler;
    private final ReadRequest readRequest;
    private final Context context;

    public ReadBoardSteps(RequestHandler requestHandler, ReadRequest readRequest,
                          ResponseHandler responseHandler, Context context) {

        this.requestHandler = requestHandler;
        this.readRequest = readRequest;
        this.context = context;

        Then("I can read created board details", () -> {
            Response response = readBoard(CommonValues.BOARD_NAME);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
            assertThat(response.getBody().jsonPath().getString("name")).isEqualTo(CommonValues.BOARD_NAME);

        });

        Then("I should not see this board any more", () -> {
            Response response = readBoard(CommonValues.BOARD_NAME);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        });

        Then("I can read board details with board name {string}", (String boardName) -> {
            Response response = readBoard(boardName);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
            assertThat(response.getBody().jsonPath().getString("name")).isEqualTo(boardName);
        });

        Then("I should see an error", () -> {
            assertThat(responseHandler.getStatusCode()).toString().startsWith("4");
        });

        Then("I see new board name {string}", (String boardName) -> {
            Response response = readBoard(CommonValues.BOARD_NAME);
            assertThat(response.getBody().jsonPath().getString("name")).isEqualTo(boardName);
        });
    }

    private Response readBoard(String boardName) {
        String boardId = context.getBoards().get(boardName);
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addPathParam("id", boardId);

        return readRequest.read(requestHandler);
    }
}
