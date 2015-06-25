package uk.co.automatictester.lightning.params.validators;

import com.beust.jcommander.ParameterException;
import org.testng.annotations.Test;

public class BooleanValidatorTest {

    @Test
    public void testValidateTrue() {
        new BooleanValidator().validate("-skipSchemaValidation", "true");
    }

    @Test
    public void testValidateFalse() {
        new BooleanValidator().validate("-skipSchemaValidation", "false");
    }

    @Test(expectedExceptions = ParameterException.class, expectedExceptionsMessageRegExp = "Parameter '-skipSchemaValidation' should be boolean \\(true or false\\)")
    public void testValidateOther() {
        new BooleanValidator().validate("-skipSchemaValidation", "True");
    }
}