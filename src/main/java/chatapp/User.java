
package chatapp;

public class User {

    private final String name;
    private final ChatServer server;

    public User(String name, ChatServer server) {
        this.name = name;
        this.server = server;
    }

    public String getName() {
        return name;
    }

    ChatServer getServer() {
        return server;
    }
}
