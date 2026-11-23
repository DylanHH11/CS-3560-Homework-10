
package chatapp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Message sendMessage(User sender, List<User> recipients, String content) {
        return null;
    }
}
