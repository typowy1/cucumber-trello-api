package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.hamcrest.core.CombinableMatcher;
import pl.akademiaqa.api.trello.boards.DeleteBoardRequest;
import pl.akademiaqa.commom.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class DeleteBoardSteps {

    private final DeleteBoardRequest deleteBoardRequest;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;


    @When("I delete existing board")
    public void i_delete_existing_board() {

        String boardId = context.getBoards().get(CommonValues.BOARD_NAME);

        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addPathParam("id", boardId);
        responseHandler.setResponse(deleteBoardRequest.deleteBoardRequest(requestHandler));

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
