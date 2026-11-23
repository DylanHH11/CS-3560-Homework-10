
package chatapp;

import java.util.List;

public class ChatHistory {

    private final User owner;

    public ChatHistory(User owner) {
        this.owner = owner;
    }

    public void addMessage(Message message) {
        // Not implemented yet
    }

    public List<Message> getMessages() {
        return List.of();
    }
}
