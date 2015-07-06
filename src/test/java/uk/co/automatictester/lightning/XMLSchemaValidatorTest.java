package uk.co.automatictester.lightning;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.exceptions.XMLFileValidationException;

import static uk.co.automatictester.lightning.data.TestData.TEST_SET_2_0_0;
import static uk.co.automatictester.lightning.data.TestData.TEST_SET_NOT_VALID;

public class XMLSchemaValidatorTest {

    @Test
    public void verifyValidateValidXML() {
        new XMLSchemaValidator().validate(TEST_SET_2_0_0);
    }

    @Test(expectedExceptions = XMLFileValidationException.class)
    public void verifyValidateInvalidXML() {
        new XMLSchemaValidator().validate(TEST_SET_NOT_VALID);
    }
}