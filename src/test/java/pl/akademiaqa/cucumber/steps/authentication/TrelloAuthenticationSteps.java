package pl.akademiaqa.cucumber.steps.authentication;

import io.cucumber.java8.En;
import lombok.RequiredArgsConstructor;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.trello.TrelloAuthentication;

@RequiredArgsConstructor
public class TrelloAuthenticationSteps implements En {

    public TrelloAuthenticationSteps(TrelloAuthentication trelloAuthentication, RequestHandler requestHandler) {

        Given("I am authenticated to Trello", () -> {
            requestHandler.addQueryParam("key", trelloAuthentication.getKey());
            requestHandler.addQueryParam("token", trelloAuthentication.getToken());
        });

        Given("I am not authenticated to Trello", () -> {
            requestHandler.addQueryParam("key", "");
            requestHandler.addQueryParam("token", "");
        });
    }
}
