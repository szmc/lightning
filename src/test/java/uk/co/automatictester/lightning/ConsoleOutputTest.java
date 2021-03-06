package uk.co.automatictester.lightning;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ConsoleOutputTest {

    protected final ByteArrayOutputStream out = new ByteArrayOutputStream();

    protected void configureStream() {
        System.setOut(new PrintStream(out));
    }

    protected void revertStream() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
}
