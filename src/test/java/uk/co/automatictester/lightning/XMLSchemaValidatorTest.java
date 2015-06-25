package uk.co.automatictester.lightning;

import org.testng.annotations.Test;

import static uk.co.automatictester.lightning.data.TestData.*;

public class XMLSchemaValidatorTest {

    @Test
    public void testValidateValidXML() {
        new XMLSchemaValidator().validate(VALID_XML_FILE);
    }
}