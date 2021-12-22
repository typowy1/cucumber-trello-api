package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.api.trello.boards.CreateBoardRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.sherd.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor//wstrzykiwanie zależności w tle wstrzykuje obiekt do konstruktora i tworzy obiekt
public class createBoardSteps {

    private final CreateBoardRequest createBoardRequest;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;

    @When("I create new board")
    public void iCreateNewBoard() {
        createNewBoard(CommonValues.BOARD_NAME);
    }

    @Given("the board already exist")
    public void theBoardAlreadyExist() {
        createNewBoard(CommonValues.BOARD_NAME);
    }

    @When("I create new board {string}")
    public void iCreateNewBoard(String boardName) {
        createNewBoard(boardName);
    }

    @When("I try to create board with empty board name")
    public void iTryToCreateBoardWithEmptyBoardName() {
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addQueryParams("name", "");

        responseHandler.setResponse(createBoardRequest.createBoard(requestHandler));
    }

    @When("I try to create board when not authenticated")
    public void iTryToCreateBoardWhenNotAuthenticated() {
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addQueryParams("name", CommonValues.BOARD_NAME);

        responseHandler.setResponse(createBoardRequest.createBoard(requestHandler));
    }

    private void createNewBoard(String boardName) {
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addQueryParams("name", boardName);

        responseHandler.setResponse(createBoardRequest.createBoard(requestHandler));
        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        context.addBoard(boardName, responseHandler.getId()); //zapisujemy boarda do mapy
        System.out.println("id: " + responseHandler.getId());
    }
}
