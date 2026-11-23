
package chatapp;

import java.util.Arrays;
import java.util.List;

public class User {

    private final String name;
    private final ChatServer server;
    private final ChatHistory history;

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
        return false;
    }

    public String getRestoredDraft() {
        return null;
    }

    public java.time.Instant getRestoredTimestamp() {
        return null;
    }

}
