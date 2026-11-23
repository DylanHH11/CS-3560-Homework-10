
package chatapp;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class User {

    private final String name;
    private final ChatServer server;
    private final ChatHistory history;
    private String restoredDraft;
    private Instant restoredTimestamp;

    public User(String name, ChatServer server) {
        this.name = name;
        this.server = server;
        this.history = new ChatHistory(this);
    }

    public String getName() {
        return name;
    }

    ChatServer getServer() {
        return server;
    }


    public ChatHistory getChatHistory() {
        return history;
    }

    public List<Message> getHistory() {
        return history.getMessages();
    }

    public Message sendMessage(String content, User... recipients) {
        return server.sendMessage(this, Arrays.asList(recipients), content);
    }

    void recordSentMessage(Message message) {
        history.addMessage(message);
    }

    void receiveMessage(Message message) {
        history.addMessage(message);
    }
    public void blockUser(User sender) {
        server.blockUser(this, sender);
    }


    public boolean undoLastMessage() {
        Message message = history.getLastMessageSent();

        if (message == null) {
            return false;
        }

        MessageMemento snapshot = history.getMemento(message);

        if (snapshot == null) {
            return false;
        }

        if (!server.undoMessage(this, message)) {
            return false;
        }

        restoredDraft = snapshot.getContent();
        restoredTimestamp = snapshot.getTimestamp();

        return true;
    }

    public String getRestoredDraft() {
        return restoredDraft;
    }

    public Instant getRestoredTimestamp() {
        return restoredTimestamp;
    }

    void removeMessage(Message message) {
        history.removeMessage(message);
    }

}
