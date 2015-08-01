package uk.co.automatictester.lightning.utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import uk.co.automatictester.lightning.exceptions.XMLFileMissingElementException;
import uk.co.automatictester.lightning.exceptions.XMLFileMissingElementValueException;
import uk.co.automatictester.lightning.exceptions.XMLFileNumberFormatException;
import uk.co.automatictester.lightning.exceptions.XMLFilePercentileException;

public abstract class LightningXMLProcessingHelpers {

    protected String getSubElementValueByTagName(Element element, String subElement) {
        String elementValue = getNodeByTagName(element, subElement).getTextContent();
        if (elementValue.length() == 0) {
            String parentNodeName = element.getNodeName();
            throw new XMLFileMissingElementValueException(String.format("Missing %s value for %s", subElement, parentNodeName));
        } else {
            return elementValue;
        }
    }

    protected Node getNodeByTagName(Element element, String subElement) {
        Node node = element.getElementsByTagName(subElement).item(0);
        if (node == null) {
            throw new XMLFileMissingElementException(String.format("Missing element %s for %s", subElement, element.getNodeName()));
        } else {
            return node;
        }
    }

    protected String getTestName(Element element) {
        return getSubElementValueByTagName(element, "testName");
    }

    protected String getTestDescription(Element element) {
        Node descriptionElement = element.getElementsByTagName("description").item(0);
        return (descriptionElement != null) ? descriptionElement.getTextContent() : "";
    }

    protected String getTransactionName(Element element) {
        if (element.getElementsByTagName("transactionName").getLength() != 0) {
            return element.getElementsByTagName("transactionName").item(0).getTextContent();
        } else {
            return null;
        }
    }

    protected int getIntegerValueFromElement(Element element, String subElement) {
        String elementValue = getSubElementValueByTagName(element, subElement);

        try {
            return Integer.parseInt(elementValue);
        } catch (NumberFormatException e) {
            String parentNodeName = element.getNodeName();
            throw new XMLFileNumberFormatException(String.format("Incorrect %s value for %s: %s", subElement, parentNodeName, elementValue));
        }
    }

    protected double getDoubleValueFromElement(Element element, String subElement) {
        String elementValue = getSubElementValueByTagName(element, subElement);

        try {
            return Double.parseDouble(elementValue);
        } catch (NumberFormatException e) {
            String parentNodeName = element.getNodeName();
            throw new XMLFileNumberFormatException(String.format("Incorrect %s value for %s: %s", subElement, parentNodeName, elementValue));
        }
    }

    protected int getPercentile(Element element, String subElement) {
        int elementValue = getIntegerValueFromElement(element, subElement);
        String parentNodeName = element.getNodeName();
        if (new Percentile().isPercentile(elementValue)) {
            return elementValue;
        } else {
            throw new XMLFilePercentileException(String.format("Incorrect %s value for %s: %s", subElement, parentNodeName, elementValue));
        }
    }

}
