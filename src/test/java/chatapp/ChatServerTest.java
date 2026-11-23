
package chatapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChatServerTest {

    @Test
    void registersUser() {
        ChatServer server = new ChatServer();
        User user1 = new User("user1", server);

        server.registerUser(user1);

        assertTrue(server.isRegistered(user1));
    }
}
