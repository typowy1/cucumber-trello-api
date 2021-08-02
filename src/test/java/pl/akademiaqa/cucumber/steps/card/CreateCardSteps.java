package pl.akademiaqa.cucumber.steps.card;

import io.cucumber.java8.En;
import org.apache.http.HttpStatus;
import pl.akademiaqa.api.trello.CreateRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

import static org.assertj.core.api.Assertions.*;

public class CreateCardSteps implements En {

    public CreateCardSteps(Context context, RequestHandler requestHandler,
                           ResponseHandler responseHandler, CreateRequest createRequest) {

        Given("I create card {string} on {string}", (String cardName, String listName) -> {
            String listId = context.getLists().get(listName);

            requestHandler.setEndpoint(TrelloUrl.CARDS);
            requestHandler.addQueryParam("idList", listId);
            requestHandler.addQueryParam("name", cardName);

            responseHandler.setResponse(createRequest.create(requestHandler));
            assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

            context.addCard(cardName, responseHandler.getId());
        });
    }
}
