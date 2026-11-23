
package chatapp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class ChatServer {

    private final Set<User> users = new HashSet<>();

    public void registerUser(User user) {
        if (user.getServer() != this) {
            throw new IllegalArgumentException(
                    "User belongs to a different server");
        }

        users.add(user);
    }

    public boolean isRegistered(User user) {
        return users.contains(user);
    }

    public void unregisterUser(User user) {
    users.remove(user);
    }



    public Message sendMessage(User sender,
                               List<User> requestedRecipients,
                               String content) {

        if (!isRegistered(sender)) {
            throw new IllegalStateException(
                    "Sender is not registered");
        }

        if (requestedRecipients == null
                || requestedRecipients.isEmpty()) {
            return null;
        }

        List<User> recipients = new ArrayList<>();

        for (User recipient : requestedRecipients) {
            if (recipient != null
                    && recipient != sender
                    && isRegistered(recipient)) {

                recipients.add(recipient);
            }
        }

        if (recipients.isEmpty()) {
            return null;
        }

        Message message = new Message(
                sender, recipients, content);

        sender.recordSentMessage(message);

        for (User recipient : recipients) {
            recipient.receiveMessage(message);
        }

        return message;
    }
    public void blockUser(User receiver, User sender) {
        // Not implemented yet
    }


}
