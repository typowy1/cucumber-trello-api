package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.api.trello.boards.UpdateBoardRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.sherd.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class UpdateBoardSteps {
    private final RequestHandler requestHandler;
    private final UpdateBoardRequest updateBoardRequest;
    private final ResponseHandler responseHandler;
    private final Context context;

    @When("I update board name {string}")
    public void i_update_board_name(String newBoardName) {
        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addPathParams("id", context.getBoards().get(CommonValues.BOARD_NAME));
        requestHandler.addQueryParams("name", newBoardName);

        responseHandler.setResponse(updateBoardRequest.updateBoard(requestHandler));
        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
