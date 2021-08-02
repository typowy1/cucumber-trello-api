package pl.akademiaqa.cucumber.steps.card;

import io.cucumber.java8.En;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.UpdateRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

public class UpdateCardSteps implements En {

    public UpdateCardSteps(Context context, RequestHandler requestHandler,
                           ResponseHandler responseHandler, UpdateRequest updateRequest) {

        When("I move {string} to {string} list", (String cardName, String listName) -> {
            String listId = context.getLists().get(listName);
            String cardId = context.getCards().get(cardName);

            requestHandler.setEndpoint(TrelloUrl.CARDS);
            requestHandler.addPathParam("id", cardId);
            requestHandler.addQueryParam("idList", listId);

            responseHandler.setResponse(updateRequest.update(requestHandler));
            Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        });
    }
}
