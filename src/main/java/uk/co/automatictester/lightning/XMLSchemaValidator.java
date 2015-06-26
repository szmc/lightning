package uk.co.automatictester.lightning;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

public class XMLSchemaValidator {

    public void validate(String xmlFile) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
            SAXSource xsdFile = new SAXSource(new InputSource(this.getClass().getResourceAsStream("/lightning-0.1.0.xsd")));
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFile)));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
