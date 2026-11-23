package chatapp;

import java.util.Iterator;
import java.util.stream.Collectors;

public class ChatDriver {

    private static void printConversation(
            User owner, User otherUser) {

        Iterator<Message> iterator = owner.iterator(otherUser);

        while (iterator.hasNext()) {
            Message message = iterator.next();

            String recipients = message.getRecipients()
                    .stream()
                    .map(User::getName)
                    .collect(Collectors.joining(", "));

            System.out.println(
                    message.getSender().getName()
                    + " -> "
                    + recipients
                    + ": "
                    + message.getContent());
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        User alice = new User("Alice", server);
        User bob = new User("Bob", server);
        User charlie = new User("Charlie", server);

        server.registerUser(alice);
        server.registerUser(bob);
        server.registerUser(charlie);

        System.out.println("--- Group messaging ---");

        alice.sendMessage("Hello everyone", bob, charlie);
        bob.sendMessage("Hi Alice", alice);

        System.out.println(
                "Bob's messages: " + bob.getHistory().size());

        System.out.println("--- Blocking ---");

        bob.blockUser(charlie);

        charlie.sendMessage("Hello Alice and Bob",
                            alice, bob);

        System.out.println(
                "Bob's messages after Charlie's send: "
                + bob.getHistory().size());

        System.out.println(
                "Alice's messages: "
                + alice.getHistory().size());

        System.out.println("--- Memento undo ---");

        alice.sendMessage("Undo this message", bob);

        System.out.println(
                "Bob before undo: "
                + bob.getHistory().size());

        alice.undoLastMessage();

        System.out.println(
                "Bob after undo: "
                + bob.getHistory().size());

        System.out.println(
                "Restored draft: "
                + alice.getRestoredDraft());

        System.out.println(
                "--- Alice's conversation with Bob ---");

        printConversation(alice, bob);

        System.out.println(
                "--- Alice's conversation with Charlie ---");

        printConversation(alice, charlie);
    }
}
