package pl.akademiaqa.api.trello.boards;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.akademiaqa.url.TrelloUrl;

import java.util.Map;

public class BaseRequest {

    //ustawia ustawienia dla requestu
    public RequestSpecification requestSetup(Map<String, ?> queryParams){ // ? podejście generyczne, każdy obiekt
        return  new RequestSpecBuilder()
                .setBaseUri(TrelloUrl.BASE_URL)
                .setContentType(ContentType.JSON)
                .addQueryParams(queryParams)
                .build();
    }

    public RequestSpecification requestSetup(Map<String, ?> queryParams, Map<String, ?> pathParams){ // ? podejście generyczne, każdy obiekt
        return  new RequestSpecBuilder()
                .setBaseUri(TrelloUrl.BASE_URL)
                .setContentType(ContentType.JSON)
                .addQueryParams(queryParams)
                .addPathParams(pathParams)
                .build();
    }
}
