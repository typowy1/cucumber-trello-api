package pl.akademiaqa.handlers.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
public class RequestHandler {
    // tu będą trzymane dane dla naszych requestów

    @Setter // dla jednego pola ustawiamy setter
    private String endpoint;
    private Map<String, String> queryParams = new HashMap<>();
    private Map<String, String> pathParams = new HashMap<>();

    public void addQueryParams(String key, String value){//metoda do dodawania wartosci
        queryParams.put(key, value);
    }

    public void addPathParams(String key, String value){//metoda do dodawania wartosci
        pathParams.put(key, value);
    }
}
