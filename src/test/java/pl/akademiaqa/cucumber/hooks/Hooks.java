package pl.akademiaqa.cucumber.hooks;

import io.cucumber.java8.En;
import pl.akademiaqa.api.trello.DeleteRequest;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.handlers.trello.TrelloAuthentication;
import pl.akademiaqa.url.TrelloUrl;

import static org.assertj.core.api.Assertions.assertThat;

public class Hooks implements En {

    public Hooks(Context context, RequestHandler requestHandler, ResponseHandler responseHandler,
                 DeleteRequest deleteRequest, TrelloAuthentication trelloAuthentication) {

        After("@cleanup", () -> {
            context.getBoards().values().stream()
                    .forEach(boardId -> {
                        requestHandler.setEndpoint(TrelloUrl.BOARDS);
                        requestHandler.getPathParams().put("id", boardId);
                        responseHandler.setResponse(deleteRequest.delete(requestHandler));
                        assertThat(responseHandler.getStatusCode()).isEqualTo(200);
                    });
        });

        BeforeStep("@authenticated", () -> {
            clearRequestData(requestHandler);
            requestHandler.getQueryParams().put("key", trelloAuthentication.getKey());
            requestHandler.getQueryParams().put("token", trelloAuthentication.getToken());
        });

        BeforeStep("@not_authenticated", () -> {
            clearRequestData(requestHandler);
        });
    }

    private void clearRequestData(RequestHandler requestHandler) {
        requestHandler.getQueryParams().clear();
        requestHandler.getPathParams().clear();
        requestHandler.setEndpoint(null);
    }
}
