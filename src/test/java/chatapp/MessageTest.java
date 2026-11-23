
package chatapp;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    void storesMessageProperties() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        Message message =
                new Message(alice, List.of(bob), "Hello Bob");

        assertSame(alice, message.getSender());
        assertEquals(List.of(bob), message.getRecipients());
        assertEquals("Hello Bob", message.getContent());
        assertNotNull(message.getTimestamp());
    }
}
