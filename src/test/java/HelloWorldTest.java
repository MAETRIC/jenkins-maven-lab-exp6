import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class HelloWorldTest {
    @Test
    void mainPrintsTheLabGreeting() {
        PrintStream original = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (PrintStream captured = new PrintStream(output, true, StandardCharsets.UTF_8)) {
            System.setOut(captured);
            HelloWorld.main(new String[0]);
        } finally {
            System.setOut(original);
        }
        assertEquals("Hello from Jenkins CI Pipeline! Version 2." + System.lineSeparator(),
                output.toString(StandardCharsets.UTF_8));
    }
}
