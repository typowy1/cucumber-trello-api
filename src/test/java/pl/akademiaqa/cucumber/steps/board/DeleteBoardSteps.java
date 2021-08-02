package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java8.En;
import org.apache.http.HttpStatus;
import pl.akademiaqa.api.trello.DeleteRequest;
import pl.akademiaqa.commom.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

import static org.assertj.core.api.Assertions.*;

public class DeleteBoardSteps implements En {

    public DeleteBoardSteps(DeleteRequest deleteRequest, RequestHandler requestHandler,
                            ResponseHandler responseHandler, Context context) {

        When("I delete existing board", () -> {
            String boardId = context.getBoards().get(CommonValues.BOARD_NAME);

            requestHandler.setEndpoint(TrelloUrl.BOARDS);
            requestHandler.addPathParam("id", boardId);
            responseHandler.setResponse(deleteRequest.delete(requestHandler));

            assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        });
    }
}
