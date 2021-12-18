package pl.akademiaqa.handlers.sherd;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Context {
    private Map<String, String> boards = new HashMap<>();

    public void addBoard(String boardName, String boardId) {
        boards.put(boardName, boardId);
    }
}
