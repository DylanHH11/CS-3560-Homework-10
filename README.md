# CS 3560 Homework 10

## Behavioral Design Patterns

This project implements a chat application using the
Mediator, Memento, and Iterator design patterns.

### Mediator

ChatServer controls message delivery, user registration,
user removal, group messaging, and blocked senders.

Users communicate through ChatServer rather than sending
messages directly to other users.

### Memento

MessageMemento preserves the original content and timestamp
of a message.

ChatHistory stores snapshots of messages sent by its owner.

Users can undo their most recently sent message and restore
the original draft.

### Iterator

IterableByUser defines the conversation search interface.

searchMessagesByUser implements Java's Iterator interface
to retrieve messages exchanged with a specified user.

Both User and ChatHistory implement IterableByUser.

## Running the project

    mvn clean test

Run the JUnit suite:

    mvn -Dtest=Homework10Suite test

Run the application driverr:

    java -cp target/classes chatapp.ChatDriver
