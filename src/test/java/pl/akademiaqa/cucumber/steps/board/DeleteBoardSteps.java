package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.cucumber.steps.api.trello.boards.DeleteBoardRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.sherd.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class DeleteBoardSteps {
    private final DeleteBoardRequest deleteBoardRequest;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;

    @When("I delete existing board")
    public void iDeleteExistingBoard() {
        String boardId = context.getBoards().get(CommonValues.BOARD_NAME);//za pomocą nazwy wyciągamy id boarda z mapy

        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addPathParams("id",boardId);

        responseHandler.setResponse(deleteBoardRequest.deleteBoardRequest(requestHandler));
    }
}
