
package chatapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ChatHistory {

    private final User owner;
    private final List<Message> messages = new ArrayList<>();

    private final Map<Message, MessageMemento> sentSnapshots =
            new HashMap<>();

    public ChatHistory(User owner) {
        this.owner = owner;
    }

    public void addMessage(Message message) {
        messages.add(message);

        if (message.getSender() == owner) {
            sentSnapshots.put(message, message.saveToMemento());
        }
    }

    public List<Message> getMessages() {
        return List.copyOf(messages);
    }


    public Message getLastMessageSent() {
        for (int i = messages.size() - 1; i >= 0; i--) {
            Message message = messages.get(i);

            if (message.getSender() == owner) {
                return message;
            }
        }

        return null;
    }


    public MessageMemento getMemento(Message message) {
        return sentSnapshots.get(message);
    }


    public boolean removeMessage(Message message) {
        boolean removed = messages.remove(message);

        if (removed) {
            sentSnapshots.remove(message);
        }

        return removed;
    }

}
