package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java8.En;
import org.apache.http.HttpStatus;
import pl.akademiaqa.api.trello.CreateRequest;
import pl.akademiaqa.commom.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

import static org.assertj.core.api.Assertions.*;

public class CreateBoardSteps implements En {

    private final CreateRequest createBoardRequest;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;

    public CreateBoardSteps(CreateRequest createBoardRequest, RequestHandler requestHandler,
                            ResponseHandler responseHandler, Context context) {

        this.createBoardRequest = createBoardRequest;
        this.requestHandler = requestHandler;
        this.responseHandler = responseHandler;
        this.context = context;

        When("I create new board", () -> {
            createNewBoard(CommonValues.BOARD_NAME);
        });

        Given("the board already exist", () -> {
            createNewBoard(CommonValues.BOARD_NAME);
        });

        When("I create new board {string}", (String boardName) -> {
            createNewBoard(boardName);
        });

        When("I try to create board with empty board name", () -> {
            requestHandler.setEndpoint(TrelloUrl.BOARDS);
            requestHandler.addQueryParam("name", "");

            responseHandler.setResponse(createBoardRequest.create(requestHandler));
        });

        When("I try to create new board when not authenticated", () -> {
            requestHandler.setEndpoint(TrelloUrl.BOARDS);
            requestHandler.addQueryParam("name", CommonValues.BOARD_NAME);

            responseHandler.setResponse(createBoardRequest.create(requestHandler));
        });
    }

    private void createNewBoard(String boardName) {
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addQueryParam("name", boardName);

        responseHandler.setResponse(createBoardRequest.create(requestHandler));
        assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        context.addBoard(boardName, responseHandler.getId());
    }
}
