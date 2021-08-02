package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.CreateRequest;
import pl.akademiaqa.commom.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class CreateBoardSteps {

    private final CreateRequest createBoardRequest;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;

    @When("I create new board")
    public void i_create_new_board() {
        createNewBoard(CommonValues.BOARD_NAME);
    }

    @Given("the board already exist")
    public void the_board_already_exist() {
        createNewBoard(CommonValues.BOARD_NAME);
    }

    @When("I create new board {string}")
    public void i_create_new_board(String boardName) {
        createNewBoard(boardName);
    }

    @When("I try to create board with empty board name")
    public void i_try_to_create_board_with_empty_board_name() {
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addQueryParam("name", "");

        responseHandler.setResponse(createBoardRequest.create(requestHandler));
    }

    @When("I try to create new board when not authenticated")
    public void i_try_to_create_new_board_when_not_authenticated() {
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addQueryParam("name", CommonValues.BOARD_NAME);

        responseHandler.setResponse(createBoardRequest.create(requestHandler));
    }

    private void createNewBoard(String boardName) {
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addQueryParam("name", boardName);

        responseHandler.setResponse(createBoardRequest.create(requestHandler));
        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        context.addBoard(boardName, responseHandler.getId());
    }
}
