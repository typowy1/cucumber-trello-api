package pl.akademiaqa.url;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // prywatny konstruktor, nikt nie utworzy obiektu tej klasy, bedziemy w sposób statyczny odwoływac się do pól tej klasy
public class TrelloUrl {
    public static final String BASE_URL = "https://api.trello.com/1/";
    public static final String BOARDS = "boards/";
}
