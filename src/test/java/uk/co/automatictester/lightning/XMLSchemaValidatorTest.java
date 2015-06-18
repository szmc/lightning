package uk.co.automatictester.lightning;

import org.testng.annotations.Test;
import com.beust.jcommander.ParameterException;

public class XMLSchemaValidatorTest {

    @Test
    public void testValidateValidXML() {
        XMLSchemaValidator.validate("src/test/resources/XMLSchemaValidatorTest_valid.xml");
    }

    @Test(expectedExceptions = ParameterException.class, expectedExceptionsMessageRegExp = "XML file.*is valid.*")
    public void testValidateInvalidXML() {
        XMLSchemaValidator.validate("src/test/resources/XMLSchemaValidatorTest_invalid.xml");
    }

    @Test(expectedExceptions = ParameterException.class, expectedExceptionsMessageRegExp = "Error accessing.*for schema validation.*")
    public void testValidateNonexistentXML() {
        XMLSchemaValidator.validate("src/test/resources/nonexistent.xml");
    }
}