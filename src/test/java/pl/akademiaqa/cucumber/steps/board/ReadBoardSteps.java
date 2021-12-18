package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.cucumber.steps.api.trello.boards.CreateBoardRequest;
import pl.akademiaqa.cucumber.steps.api.trello.boards.ReadBoardRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.sherd.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class ReadBoardSteps {

    private final CreateBoardRequest createBoardRequest;
    private final RequestHandler requestHandler;
    private final ReadBoardRequest readBoardRequest;
    private final ResponseHandler responseHandler;
    private final Context context;

    @Then("I can read created board details")
    public void iCanReadCreatedBoardDetails() {
        Response response = readBoard();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(response.getBody().jsonPath().getString("name")).isEqualTo(CommonValues.BOARD_NAME);
    }

    @Then("I should not see this board any more")
    public void iShouldNotSeeThisBoardAnyMore() {
        Response response = readBoard();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    private Response readBoard() {
        String boardId = context.getBoards().get(CommonValues.BOARD_NAME);

        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addPathParams("id", boardId);
        System.out.println("id " + responseHandler.getId());

        return readBoardRequest.readBoard(requestHandler);
    }
}
