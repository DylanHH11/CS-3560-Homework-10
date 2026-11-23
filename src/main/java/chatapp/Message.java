
package chatapp;

import java.time.Instant;
import java.util.List;

public final class Message {

    public Message(User sender, List<User> recipients,
                   String content) {
        // Not implemented yet
    }

    public User getSender() {
        return null;
    }

    public List<User> getRecipients() {
        return List.of();
    }

    public Instant getTimestamp() {
        return null;
    }

    public String getContent() {
        return null;
    }
}
