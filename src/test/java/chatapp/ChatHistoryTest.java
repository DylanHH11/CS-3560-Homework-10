
package chatapp;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChatHistoryTest {

    @Test
    void addsMessageToHistory() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        ChatHistory history = new ChatHistory(alice);
        Message message =
                new Message(alice, List.of(bob), "Hello");

        history.addMessage(message);

        assertEquals(1, history.getMessages().size());
        assertSame(message, history.getMessages().get(0));
    }

    @Test
    void returnsLastMessageSentByOwner() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.registerUser(alice);
        server.registerUser(bob);

        Message sent = alice.sendMessage("Hi Bob", bob);
        bob.sendMessage("Hi Alice", alice);

        assertSame(
                sent,
                alice.getChatHistory().getLastMessageSent());
    }

}
