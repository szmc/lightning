package uk.co.automatictester.lightning.params.validators;

import com.beust.jcommander.ParameterException;
import org.testng.annotations.Test;

import static uk.co.automatictester.lightning.data.TestData.EXISTING_CSV_FILE;
import static uk.co.automatictester.lightning.data.TestData.NONEXISTENT_CSV_FILE;

public class FileValidatorTest {

    @Test
    public void verifyValidateExisting() {
        new FileValidator().validate("-csv", EXISTING_CSV_FILE);
    }

    @Test(expectedExceptions = ParameterException.class, expectedExceptionsMessageRegExp = "Error reading file: " + NONEXISTENT_CSV_FILE)
    public void verifyValidateNonexistent() {
        new FileValidator().validate("-csv", NONEXISTENT_CSV_FILE);
    }
}