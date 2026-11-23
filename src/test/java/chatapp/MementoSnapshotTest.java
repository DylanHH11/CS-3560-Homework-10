package chatapp;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MementoSnapshotTest {

    @Test
    void savesMessageContentAndTimestamp() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        Message message =
                new Message(alice, List.of(bob), "Hello Bob");

        MessageMemento snapshot = message.saveToMemento();

        assertNotNull(snapshot);
        assertEquals("Hello Bob", snapshot.getContent());
        assertEquals(message.getTimestamp(),
                     snapshot.getTimestamp());
    }

    @Test
    void historyStoresSnapshotForSentMessages() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        Message message =
                new Message(alice, List.of(bob), "Saved message");

        alice.getChatHistory().addMessage(message);

        MessageMemento snapshot =
                alice.getChatHistory().getMemento(message);

        assertNotNull(snapshot);
        assertEquals("Saved message", snapshot.getContent());
    }

    @Test
    void receivedMessagesDoNotCreateSnapshots() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        Message message =
                new Message(alice, List.of(bob), "Hello");

        bob.getChatHistory().addMessage(message);

        assertNull(bob.getChatHistory().getMemento(message));
    }
}
