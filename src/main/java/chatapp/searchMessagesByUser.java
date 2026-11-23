package chatapp;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class searchMessagesByUser implements Iterator<Message> {

    public searchMessagesByUser(
            User owner,
            User otherUser,
            List<Message> messages) {
        // Not implemented yet
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Message next() {
        throw new NoSuchElementException();
    }
}
