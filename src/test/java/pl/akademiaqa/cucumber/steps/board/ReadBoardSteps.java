package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.api.trello.boards.CreateBoardRequest;
import pl.akademiaqa.api.trello.boards.ReadBoardRequest;
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
        Response response = readBoard(CommonValues.BOARD_NAME);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(response.getBody().jsonPath().getString("name")).isEqualTo(CommonValues.BOARD_NAME);
    }

    @Then("I should not see this board any more")
    public void iShouldNotSeeThisBoardAnyMore() {
        Response response = readBoard(CommonValues.BOARD_NAME);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Then("I can read board details with board name {string}")
    public void i_can_read_board_details_with_board_name(String boardName) {
        Response response = readBoard(boardName);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(response.getBody().jsonPath().getString("name")).isEqualTo(boardName);
    }

    @Then("I should see an error")
    public void iShouldSeeAnError() { //sprawdzimy czy status code zaczyne sie od 4
        String statusCode = String.valueOf(responseHandler.getStatusCode());
        Assertions.assertThat(statusCode).startsWith("4");
//        Assertions.assertThat(responseHandler.getStatusCode()).toString().startsWith("4");
    }

    @Then("I see new board name {string}")
    public void i_see_new_board_name(String boardName) {
        Response response = readBoard(CommonValues.BOARD_NAME);
        Assertions.assertThat(response.getBody().jsonPath().getString("name")).isEqualTo(boardName);
    }

    private Response readBoard(String boardName) {
        String boardId = context.getBoards().get(boardName);

        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addPathParams("id", boardId);
        System.out.println("id " + responseHandler.getId());

        return readBoardRequest.readBoard(requestHandler);
    }
}
