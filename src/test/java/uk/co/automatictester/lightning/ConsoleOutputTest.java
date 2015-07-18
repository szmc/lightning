package uk.co.automatictester.lightning;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleOutputTest {

    protected final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @BeforeMethod
    protected void configureStreams() {
        System.setOut(new PrintStream(out));
    }

    @AfterMethod
    protected void revertStreams() {
        System.setOut(null);
    }
}
