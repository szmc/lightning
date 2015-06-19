package uk.co.automatictester.lightning;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import uk.co.automatictester.lightning.tests.AvgRespTimeTest;
import uk.co.automatictester.lightning.tests.PassedTransactionsTest;
import uk.co.automatictester.lightning.tests.RespTimeStdDevTest;
import uk.co.automatictester.lightning.tests.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestSet {

    private static List<Test> tests = new ArrayList<Test>();
    private static int failureCount = 0;

    public static void load(String xmlFile) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDoc = db.parse(new File(xmlFile));
            xmlDoc.getDocumentElement().normalize();
            NodeList avgRespTimeTestNodes = xmlDoc.getElementsByTagName("avgRespTimeTest");

            for (int i = 0; i < avgRespTimeTestNodes.getLength(); i++) {
                Element avgRespTimeTestElement = (Element) avgRespTimeTestNodes.item(i);

                String name = getTestName(avgRespTimeTestElement);
                String description = getTestDescription(avgRespTimeTestElement);
                String transactionName = getTransactionName(avgRespTimeTestElement);
                long maxAvgRespTime = Long.parseLong(getElementByTagName(avgRespTimeTestElement, "maxAvgRespTime"));

                AvgRespTimeTest avgRespTimeTest = new AvgRespTimeTest(name, description, transactionName, maxAvgRespTime);
                tests.add(avgRespTimeTest);
            }

            NodeList respTimeStdDevTestNodes = xmlDoc.getElementsByTagName("respTimeStdDevTest");
            for (int i = 0; i < respTimeStdDevTestNodes.getLength(); i++) {
                Element respTimeStdDevTestElement = (Element) respTimeStdDevTestNodes.item(i);

                String name = getTestName(respTimeStdDevTestElement);
                String description = getTestDescription(respTimeStdDevTestElement);
                String transactionName = getTransactionName(respTimeStdDevTestElement);
                long maxRespTimeStdDevTime = Long.parseLong(getElementByTagName(respTimeStdDevTestElement, "maxRespTimeStdDev"));

                RespTimeStdDevTest respTimeStdDevTest = new RespTimeStdDevTest(name, description, transactionName, maxRespTimeStdDevTime);
                tests.add(respTimeStdDevTest);
            }

            NodeList passedTransactionsTestNodes = xmlDoc.getElementsByTagName("passedTransactionsTest");
            for (int i = 0; i < passedTransactionsTestNodes.getLength(); i++) {
                Element passedTransactionsElement = (Element) passedTransactionsTestNodes.item(i);

                String name = getTestName(passedTransactionsElement);
                String description = getTestDescription(passedTransactionsElement);
                String transactionName = getTransactionName(passedTransactionsElement);

                int allowedNumberOfFailedTransactions = Integer.parseInt(getElementByTagName(passedTransactionsElement, "allowedNumberOfFailedTransactions"));

                PassedTransactionsTest passedTransactionsTest = new PassedTransactionsTest(name, description, transactionName, allowedNumberOfFailedTransactions);
                tests.add(passedTransactionsTest);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void execute(JMeterTransactions originalJMeterTransactions) {
        for (Test test : tests) {
            test.execute(originalJMeterTransactions);
            if (test.isFailed()) failureCount++;
            System.out.println(test.getReport());
        }
    }

    public static int reportTestSetResult() {
        int testCount = tests.size();
        int passCount = testCount - failureCount;

        System.out.println("============= EXECUTION SUMMARY =============");
        System.out.println("Tests executed:   " + testCount);
        System.out.println("Tests passed:     " + passCount);
        System.out.println("Tests failed:     " + failureCount);
        System.out.println("Test set status:  " + ((failureCount != 0) ? "FAIL" : "Pass") + System.lineSeparator());

        return failureCount;
    }

    private static String getTestName(Element xmlElement) {
        return xmlElement.getElementsByTagName("testName").item(0).getTextContent();
    }

    private static String getTransactionName(Element xmlElement) {
        return xmlElement.getElementsByTagName("transactionName").item(0).getTextContent();
    }

    private static String getTestDescription(Element xmlElement) {
        Node descriptionElement = xmlElement.getElementsByTagName("description").item(0);
        return (descriptionElement != null) ? descriptionElement.getTextContent() : "";
    }

    private static String getElementByTagName(Element xmlElement, String tagName) {
        return xmlElement.getElementsByTagName(tagName).item(0).getTextContent();
    }

}
