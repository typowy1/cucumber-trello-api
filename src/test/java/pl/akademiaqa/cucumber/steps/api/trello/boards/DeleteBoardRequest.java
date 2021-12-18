package pl.akademiaqa.cucumber.steps.api.trello.boards;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import pl.akademiaqa.handlers.api.RequestHandler;

import static io.restassured.RestAssured.given;

@RequiredArgsConstructor
public class DeleteBoardRequest {

    private final BaseRequest baseRequest;

    public Response deleteBoardRequest(RequestHandler requestHandler) {

        return given()
                .spec(baseRequest.requestSetup(requestHandler.getQueryParams(), requestHandler.getPathParams()))//pobieramy mape z parametrami
                .when()
                .log().all()
                .delete(requestHandler.getEndpoint() + "{id}")//bazowy adres jest w baseRequest.requestSetup
                .then()
                .log().all()//logowanie jesli błąd log.ifError
                .extract()
                .response();
    }
}
