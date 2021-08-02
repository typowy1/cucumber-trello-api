package pl.akademiaqa.cucumber.steps.list;

import io.cucumber.java8.En;
import org.apache.http.HttpStatus;
import pl.akademiaqa.api.trello.CreateRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

import static org.assertj.core.api.Assertions.*;

public class CreateListSteps implements En {

    public CreateListSteps(RequestHandler requestHandler, ResponseHandler responseHandler,
                           Context context, CreateRequest createRequest) {

        Given("I create list {string} on {string}", (String listName, String boardName) -> {
            String boardId = context.getBoards().get(boardName);

            requestHandler.setEndpoint(TrelloUrl.LISTS);
            requestHandler.addQueryParam("idBoard", boardId);
            requestHandler.addQueryParam("name", listName);

            responseHandler.setResponse(createRequest.create(requestHandler));
            assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

            context.addList(listName, responseHandler.getId());
        });
    }
}
