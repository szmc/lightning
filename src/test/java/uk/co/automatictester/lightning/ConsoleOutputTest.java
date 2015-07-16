package uk.co.automatictester.lightning;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleOutputTest {

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeMethod
    protected void configureStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterMethod
    protected void revertStreams() {
        System.setOut(null);
    }
}
