
package chatapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChatServerTest {

    @Test
    void registersUser() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);

        server.registerUser(alice);

        assertTrue(server.isRegistered(alice));
    }
    
    @Test
    void unregistersUser() {
    ChatServer server = new ChatServer();
    User alice = new User("Alice", server);

    server.registerUser(alice);
    server.unregisterUser(alice);

    assertFalse(server.isRegistered(alice));
    }

    @Test
    void sendsMessageToOneUser() {
    ChatServer server = new ChatServer();
    User alice = new User("Alice", server);
    User bob = new User("Bob", server);

    server.registerUser(alice);
    server.registerUser(bob);

    Message message = alice.sendMessage("Hi Bob", bob);

    assertNotNull(message);
    assertEquals(1, alice.getHistory().size());
    assertSame(message, bob.getHistory().get(0));
    }
    @Test
    void sendsMessageToMultipleUsers() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);

        server.registerUser(alice);
        server.registerUser(bob);
        server.registerUser(charlie);

        Message message =
                alice.sendMessage("Hello everyone", bob, charlie);

        assertEquals(2, message.getRecipients().size());
        assertSame(message, bob.getHistory().get(0));
        assertSame(message, charlie.getHistory().get(0));
    }

}
