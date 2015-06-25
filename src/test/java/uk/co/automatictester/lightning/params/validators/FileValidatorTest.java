package uk.co.automatictester.lightning.params.validators;

import com.beust.jcommander.ParameterException;
import org.testng.annotations.Test;

import static uk.co.automatictester.lightning.data.TestData.*;

public class FileValidatorTest {

    @Test
    public void testValidateExisting() {
        new FileValidator().validate("-csv", EXISTING_CSV_FILE);
    }

    @Test(expectedExceptions = ParameterException.class, expectedExceptionsMessageRegExp = "Error reading file: " + NONEXISTENT_CSV_FILE)
    public void testValidateNonexistent() {
        new FileValidator().validate("-csv", NONEXISTENT_CSV_FILE);
    }
}