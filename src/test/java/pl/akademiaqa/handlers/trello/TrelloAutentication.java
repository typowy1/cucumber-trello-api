package pl.akademiaqa.handlers.trello;

import groovy.lang.Singleton;
import lombok.SneakyThrows;

import java.util.Properties;

@Singleton //klase tworzymy raz, bedzie tylko jedna instancja
public class TrelloAutentication {

    private final static Properties PROPERTIES = new Properties();
    private final static String KEY = "key";
    private final static String TOKEN = "token";

    @SneakyThrows //wyjątek zostanie rzucony wyżej
    public TrelloAutentication() {
        PROPERTIES.load(getClass().getClassLoader().getResourceAsStream("trello.properties"));
    }//wczytywanie danych z pliku properties do stałej PROPERTIES

    public String getKey(){
        return PROPERTIES.getProperty(KEY);
    }

    public String getToken(){
        return PROPERTIES.getProperty(TOKEN);
    }//zwróci wartość z propertiesów
}
