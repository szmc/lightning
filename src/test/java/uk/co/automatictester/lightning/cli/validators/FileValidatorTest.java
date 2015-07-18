package uk.co.automatictester.lightning.cli.validators;

import com.beust.jcommander.ParameterException;
import org.testng.annotations.Test;

import static uk.co.automatictester.lightning.data.TestData.CSV_2_TRANSACTIONS;
import static uk.co.automatictester.lightning.data.TestData.CSV_NONEXISTENT;

public class FileValidatorTest {

    @Test
    public void verifyValidateExisting() {
        new FileValidator().validate("-csv", CSV_2_TRANSACTIONS);
    }

    @Test(expectedExceptions = ParameterException.class, expectedExceptionsMessageRegExp = "Error reading file: " + CSV_NONEXISTENT)
    public void verifyValidateNonexistent() {
        new FileValidator().validate("-csv", CSV_NONEXISTENT);
    }
}