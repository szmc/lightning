package uk.co.automatictester.lightning;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
                Element maxAvgRespTimeTestElement = (Element) avgRespTimeTestNodes.item(i);
                String name = maxAvgRespTimeTestElement.getElementsByTagName("testName").item(0).getTextContent();
                String description = "";
                if (maxAvgRespTimeTestElement.getElementsByTagName("description").item(0) != null) {
                    description = maxAvgRespTimeTestElement.getElementsByTagName("description").item(0).getTextContent();
                }
                String transactionName = maxAvgRespTimeTestElement.getElementsByTagName("transactionName").item(0).getTextContent();
                long maxAvgRespTime = Long.parseLong(maxAvgRespTimeTestElement.getElementsByTagName("maxAvgRespTime").item(0).getTextContent());

                AvgRespTimeTest avgRespTimeTest = new AvgRespTimeTest(name, description, transactionName, maxAvgRespTime);
                tests.add(avgRespTimeTest);
            }

            NodeList respTimeStdDevTestNodes = xmlDoc.getElementsByTagName("respTimeStdDevTest");
            for (int i = 0; i < respTimeStdDevTestNodes.getLength(); i++) {
                Element respTimeStdDevTestElement = (Element) respTimeStdDevTestNodes.item(i);
                String name = respTimeStdDevTestElement.getElementsByTagName("testName").item(0).getTextContent();
                String description = "";
                if (respTimeStdDevTestElement.getElementsByTagName("description").item(0) != null) {
                    description = respTimeStdDevTestElement.getElementsByTagName("description").item(0).getTextContent();
                }
                String transactionName = respTimeStdDevTestElement.getElementsByTagName("transactionName").item(0).getTextContent();
                long maxRespTimeStdDevTime = Long.parseLong(respTimeStdDevTestElement.getElementsByTagName("maxRespTimeStdDev").item(0).getTextContent());

                RespTimeStdDevTest respTimeStdDevTest = new RespTimeStdDevTest(name, description, transactionName, maxRespTimeStdDevTime);
                tests.add(respTimeStdDevTest);
            }

            NodeList passedTransactionsTestNodes = xmlDoc.getElementsByTagName("passedTransactionsTest");
            for (int i = 0; i < passedTransactionsTestNodes.getLength(); i++) {
                Element passedTransactionsElement = (Element) passedTransactionsTestNodes.item(i);
                String name = passedTransactionsElement.getElementsByTagName("testName").item(0).getTextContent();
                String description = "";
                if (passedTransactionsElement.getElementsByTagName("description").item(0) != null) {
                    description = passedTransactionsElement.getElementsByTagName("description").item(0).getTextContent();
                }
                String transactionName = passedTransactionsElement.getElementsByTagName("transactionName").item(0).getTextContent();
                int allowedNumberOfFailedTransactions = Integer.parseInt(passedTransactionsElement.getElementsByTagName("allowedNumberOfFailedTransactions").item(0).getTextContent());

                PassedTransactionsTest passedTransactionsTest = new PassedTransactionsTest(name, description, transactionName, allowedNumberOfFailedTransactions);
                tests.add(passedTransactionsTest);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.getMessage();
        }
    }

    public static void execute(JMeterTransactions originalJMeterTransactions) {
        for (Test test : tests) {
            test.execute(originalJMeterTransactions);
            failureCount += test.reportResults();
        }
    }

    public static int reportTestSetResult() {
        int testCount = tests.size();
        int passCount = testCount - failureCount;

        System.out.println("============= EXECUTION SUMMARY =============");
        System.out.println("Tests executed:   " + testCount);
        System.out.println("Tests passed:     " + passCount);
        System.out.println("Tests failed:     " + failureCount);
        System.out.println("Test set status:  " + ((failureCount != 0 ) ? "FAIL" : "Pass") + System.lineSeparator());

        return failureCount;
    }

}
