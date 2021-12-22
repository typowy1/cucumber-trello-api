package pl.akademiaqa.api.trello.boards;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import pl.akademiaqa.handlers.api.RequestHandler;

import static io.restassured.RestAssured.given;

@RequiredArgsConstructor
public class ReadBoardRequest {

    private final BaseRequest baseRequest;

    public Response readBoard(RequestHandler requestHandler) {

        return given()
                .spec(baseRequest.requestSetup(requestHandler.getQueryParams(), requestHandler.getPathParams()))//pobieramy mape z parametrami
                .when()
                .log().all()
                .get(requestHandler.getEndpoint() + "{id}")//bazowy adres jest w baseRequest.requestSetup
                .then()
                .log().all()//logowanie jesli błąd log.ifError
                .extract()
                .response();
    }
}
