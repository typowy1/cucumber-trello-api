package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
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

@RequiredArgsConstructor//wstrzykiwanie zależności w tle wstrzykuje obiekt do konstruktora i tworzy obiekt
public class createBoardSteps {

    private final CreateBoardRequest createBoardRequest;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;

    @When("I create new board")
    public void iCreateNewBoard() {
        createNewBoard();
    }

    @Given("the board already exist")
    public void theBoardAlreadyExist() {
        createNewBoard();
    }

    private void createNewBoard() {
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addQueryParams("name", CommonValues.BOARD_NAME);

        responseHandler.setResponse(createBoardRequest.createBoard(requestHandler));
        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        context.addBoard(CommonValues.BOARD_NAME, responseHandler.getId()); //zapisujemy boarda do mapy
    }
}
