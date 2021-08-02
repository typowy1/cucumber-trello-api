package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java8.En;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.UpdateRequest;
import pl.akademiaqa.commom.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

public class UpdateBoardSteps implements En {

    public UpdateBoardSteps(RequestHandler requestHandler, ResponseHandler responseHandler,
                            UpdateRequest updateRequest, Context context) {

        When("I update board name {string}", (String newBoardName) -> {
            requestHandler.setEndpoint(TrelloUrl.BOARDS);
            requestHandler.addPathParam("id", context.getBoards().get(CommonValues.BOARD_NAME));
            requestHandler.addQueryParam("name", newBoardName);

            responseHandler.setResponse(updateRequest.update(requestHandler));
            Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        });
    }
}
