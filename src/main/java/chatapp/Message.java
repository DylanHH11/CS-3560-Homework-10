
package chatapp;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public final class Message {

    private final User sender;
    private final List<User> recipients;
    private final Instant timestamp;
    private final String content;

    public Message(User sender, List<User> recipients,
                   String content) {
        this.sender = Objects.requireNonNull(sender);
        this.recipients = List.copyOf(recipients);
        this.content = Objects.requireNonNull(content);
        this.timestamp = Instant.now();
    }

    public User getSender() {
        return sender;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public MessageMemento saveToMemento() {
        return new MessageMemento(content, timestamp);
    }

}
