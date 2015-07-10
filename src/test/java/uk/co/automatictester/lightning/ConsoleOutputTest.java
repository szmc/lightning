package uk.co.automatictester.lightning;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleOutputTest {

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeClass
    protected void configureStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterClass
    protected void revertStreams() {
        System.setOut(null);
    }
}
