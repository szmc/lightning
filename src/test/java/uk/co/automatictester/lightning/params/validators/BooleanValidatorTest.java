package uk.co.automatictester.lightning.params.validators;

import com.beust.jcommander.ParameterException;
import org.testng.annotations.Test;

public class BooleanValidatorTest {

    @Test
    public void testValidateTrue() {
        new BooleanValidator().validate("var", "true");
    }

    @Test
    public void testValidateFalse() {
        new BooleanValidator().validate("var", "false");
    }

    @Test(expectedExceptions = ParameterException.class, expectedExceptionsMessageRegExp = "Parameter var should be boolean \\(true or false\\)")
    public void testValidateOther() {
        new BooleanValidator().validate("var", "True");
    }
}