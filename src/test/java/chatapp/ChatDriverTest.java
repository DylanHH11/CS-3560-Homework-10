package chatapp;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ChatDriverTest {

    @Test
    void demonstratesChatPatterns() {
        PrintStream original = System.out;
        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output));

            ChatDriver.main(new String[0]);
        } finally {
            System.setOut(original);
        }

        String result = output.toString();

        assertTrue(result.contains(
                "Bob after undo: 2"));

        assertTrue(result.contains(
                "Restored draft: Undo this message"));

        assertTrue(result.contains(
                "Alice -> Bob, Charlie: Hello everyone"));

        assertTrue(result.contains(
                "Charlie -> Alice: Hello Alice and Bob"));
    }
}
