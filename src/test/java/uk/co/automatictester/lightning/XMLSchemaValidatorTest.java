package uk.co.automatictester.lightning;

import org.testng.annotations.Test;

public class XMLSchemaValidatorTest {

    @Test
    public void testValidateValidXML() {
        XMLSchemaValidator.validate("src/test/resources/XMLSchemaValidatorTest_valid.xml");
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "XML file.*is not valid.*")
    public void testValidateInvalidXML() {
        XMLSchemaValidator.validate("src/test/resources/XMLSchemaValidatorTest_invalid.xml");
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Error accessing.*for schema validation.*")
    public void testValidateNonexistentXML() {
        XMLSchemaValidator.validate("src/test/resources/nonexistent.xml");
    }
}