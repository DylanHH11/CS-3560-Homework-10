package chatapp;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorTest {

    @Test
    void findsOnlyMessagesWithSpecifiedUser() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);

        server.registerUser(alice);
        server.registerUser(bob);
        server.registerUser(charlie);

        Message first = alice.sendMessage("Hi Bob", bob);
        alice.sendMessage("Hi Charlie", charlie);
        Message reply = bob.sendMessage("Hi Alice", alice);

        Iterator<Message> iterator = alice.iterator(bob);

        assertTrue(iterator.hasNext());
        assertSame(first, iterator.next());
        assertTrue(iterator.hasNext());
        assertSame(reply, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void includesGroupMessages() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);

        server.registerUser(alice);
        server.registerUser(bob);
        server.registerUser(charlie);

        Message group =
                alice.sendMessage("Hello everyone", bob, charlie);

        assertSame(group, alice.iterator(bob).next());
        assertSame(group, alice.iterator(charlie).next());
    }

    @Test
    void returnsNoMessagesForUnrelatedUser() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);

        server.registerUser(alice);
        server.registerUser(bob);
        server.registerUser(charlie);

        alice.sendMessage("Hi Charlie", charlie);

        assertFalse(alice.iterator(bob).hasNext());
    }

    @Test
    void throwsWhenIteratorIsExhausted() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.registerUser(alice);
        server.registerUser(bob);

        Message message = alice.sendMessage("Hello", bob);

        Iterator<Message> iterator = alice.iterator(bob);

        assertSame(message, iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class,
                     iterator::next);
    }

    @Test
    void userDelegatesToChatHistoryIterator() {
        ChatServer server = new ChatServer();
        User alice = new User("Alice", server);
        User bob = new User("Bob", server);

        server.registerUser(alice);
        server.registerUser(bob);

        Message message = alice.sendMessage("Hi Bob", bob);

        assertSame(message,
                   alice.getChatHistory().iterator(bob).next());
        assertSame(message, alice.iterator(bob).next());
    }
}
