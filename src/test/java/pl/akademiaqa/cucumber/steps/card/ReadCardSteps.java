package pl.akademiaqa.cucumber.steps.card;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.ReadRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class ReadCardSteps {

    private final Context context;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final ReadRequest readRequest;

    @Then("I see {string} on {string} list")
    public void i_see_on_list(String cardName, String listName) {
        String listId = context.getLists().get(listName);
        String cardId = context.getCards().get(cardName);

        requestHandler.setEndpoint(TrelloUrl.CARDS);
        requestHandler.addPathParam("id", cardId);

        responseHandler.setResponse(readRequest.read(requestHandler));

        Assertions.assertThat(responseHandler.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(responseHandler.getResponse().getBody().jsonPath().getString("idList")).isEqualTo(listId);
    }
}
