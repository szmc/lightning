package uk.co.automatictester.lightning;

import org.testng.annotations.Test;

import static uk.co.automatictester.lightning.data.TestData.INVALID_XML_FILE;
import static uk.co.automatictester.lightning.data.TestData.VALID_XML_FILE;

public class XMLSchemaValidatorTest {

    @Test
    public void verifyValidateValidXML() {
        new XMLSchemaValidator().validate(VALID_XML_FILE);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void verifyValidateInvalidXML() {
        new XMLSchemaValidator().validate(INVALID_XML_FILE);
    }
}