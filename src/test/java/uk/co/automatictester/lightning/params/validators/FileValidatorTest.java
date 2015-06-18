package uk.co.automatictester.lightning.params.validators;

import com.beust.jcommander.ParameterException;
import org.testng.annotations.Test;

public class FileValidatorTest {

    private static final String NONEXISTENT_FILE = "src/test/resources/nonexistent.csv";

    @Test
    public void testValidateExisting() {
        new FileValidator().validate("-csv", "src/test/resources/FileValidatorTest.csv");
    }

    @Test(expectedExceptions = ParameterException.class, expectedExceptionsMessageRegExp = "Error reading file: " + NONEXISTENT_FILE)
    public void testValidateNonexistent() {
        new FileValidator().validate("-csv", NONEXISTENT_FILE);
    }
}