package uk.co.automatictester.lightning;

import org.testng.annotations.Test;
import com.beust.jcommander.ParameterException;

public class XMLSchemaValidatorTest {

    @Test(expectedExceptions = ParameterException.class)
    public void testValidateInvalidXML() {
        XMLSchemaValidator.validate("src/test/resources/XMLSchemaValidatorTest_invalid.xml");
    }

    @Test
    public void testValidateValidXML() {
        XMLSchemaValidator.validate("src/test/resources/XMLSchemaValidatorTest_valid.xml");
    }
}