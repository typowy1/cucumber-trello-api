package pl.akademiaqa.api.trello.boards;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import pl.akademiaqa.handlers.api.RequestHandler;

import static io.restassured.RestAssured.given;

@RequiredArgsConstructor
public class CreateBoardRequest {
    private final BaseRequest baseRequest;// tu ustawiamy specyfikacje do kazdego requestu

    public Response createBoard(RequestHandler requestHandler) {

        return given()
                .spec(baseRequest.requestSetup(requestHandler.getQueryParams()))//pobieramy mape z parametrami
                .when()
                .log().all()
                .post(requestHandler.getEndpoint())//bazowy adres jest w baseRequest.requestSetup
                .then()
                .log().all()//logowanie jesli błąd log.ifError
                .extract()
                .response();
    }
}
