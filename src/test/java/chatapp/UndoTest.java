package chatapp;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UndoTest {

    @Test
    void undoRemovesGroupMessageFromAllHistories() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);

        server.registerUser(alice);
        server.registerUser(bob);
        server.registerUser(charlie);

        alice.sendMessage("Hello everyone", bob, charlie);

        assertTrue(alice.undoLastMessage());
        assertTrue(alice.getHistory().isEmpty());
        assertTrue(bob.getHistory().isEmpty());
        assertTrue(charlie.getHistory().isEmpty());
    }

    @Test
    void undoRestoresOriginalDraftAndTimestamp() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.registerUser(alice);
        server.registerUser(bob);

        Message message =
                alice.sendMessage("Original message", bob);

        assertTrue(alice.undoLastMessage());
        assertEquals("Original message",
                     alice.getRestoredDraft());
        assertEquals(message.getTimestamp(),
                     alice.getRestoredTimestamp());
        assertNull(alice.getChatHistory().getMemento(message));
    }

    @Test
    void undoOnlyRemovesLastSentMessage() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.registerUser(alice);
        server.registerUser(bob);

        Message first = alice.sendMessage("First", bob);
        alice.sendMessage("Second", bob);
        Message reply = bob.sendMessage("Reply", alice);

        assertTrue(alice.undoLastMessage());

        assertEquals(List.of(first, reply),
                     alice.getHistory());
        assertEquals(List.of(first, reply),
                     bob.getHistory());
        assertEquals("Second", alice.getRestoredDraft());
    }

    @Test
    void cannotUndoWithoutSentMessages() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.registerUser(alice);
        server.registerUser(bob);

        bob.sendMessage("Hello Alice", alice);

        assertFalse(alice.undoLastMessage());
        assertNull(alice.getRestoredDraft());
        assertEquals(1, alice.getHistory().size());
    }
}
