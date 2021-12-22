package pl.akademiaqa.cucumber.steps.authentication;

import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.trello.TrelloAutentication;

@RequiredArgsConstructor//wstrzykiwanie zależności w tle wstrzykuje obiekt do konstruktora i tworzy obiekt
public class TrelloAuthenticationSteps {

    private final TrelloAutentication trelloAutentication; // z tąd odczytujemy klucz i token
    private final RequestHandler requestHandler; //tu dodajemy nasze query parametry

    @Given("I am authenticated to Trello")
    public void iamAuthenticatedToTrello() {
        requestHandler.addQueryParams("key", trelloAutentication.getKey());
        requestHandler.addQueryParams("token", trelloAutentication.getToken());
    }

    @Given("I am not authenticated to Trello")
    public void iAmNotAuthenticatedToTrello() {
        requestHandler.addQueryParams("key", "");
        requestHandler.addQueryParams("token", "");
    }
}
