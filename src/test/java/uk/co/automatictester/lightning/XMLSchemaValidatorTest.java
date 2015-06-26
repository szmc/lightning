package uk.co.automatictester.lightning;

import org.testng.annotations.Test;

import static uk.co.automatictester.lightning.data.TestData.TEST_SET_3_0_0;
import static uk.co.automatictester.lightning.data.TestData.TEST_SET_INVALID;

public class XMLSchemaValidatorTest {

    @Test
    public void verifyValidateValidXML() {
        new XMLSchemaValidator().validate(TEST_SET_3_0_0
        );
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void verifyValidateInvalidXML() {
        new XMLSchemaValidator().validate(TEST_SET_INVALID);
    }
}