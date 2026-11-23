package chatapp;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class searchMessagesByUser implements Iterator<Message> {

    private final User owner;
    private final User otherUser;
    private final List<Message> messages;
    private int index = 0;

    public searchMessagesByUser(
            User owner,
            User otherUser,
            List<Message> messages) {

        this.owner = owner;
        this.otherUser = otherUser;
        this.messages = List.copyOf(messages);
    }

    private boolean matches(Message message) {
        if (message.getSender() == owner) {
            return message.getRecipients().contains(otherUser);
        }

        if (message.getSender() == otherUser) {
            return message.getRecipients().contains(owner);
        }

        return false;
    }

    @Override
    public boolean hasNext() {
        while (index < messages.size()
                && !matches(messages.get(index))) {
            index++;
        }

        return index < messages.size();
    }

    @Override
    public Message next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return messages.get(index++);
    }
}
