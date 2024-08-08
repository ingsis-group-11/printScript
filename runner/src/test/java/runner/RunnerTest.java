package runner;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RunnerTest {
    @Test public void testRun() throws IOException {
        Runner runner = new Runner();
        runner.run("src/test/resources/test.txt");
    }
}
