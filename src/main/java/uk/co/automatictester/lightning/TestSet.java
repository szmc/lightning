package uk.co.automatictester.lightning;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import uk.co.automatictester.lightning.tests.MaxAvgRespTimeTest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestSet {

    private static List<Test> tests = new ArrayList<Test>();

    public static void load(String xmlFile) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDoc = db.parse(new File(xmlFile));
            xmlDoc.getDocumentElement().normalize();

            NodeList maxAvgRespTimeTestNodes = xmlDoc.getElementsByTagName("maxAvgRespTimeTest");
            for (int i = 0; i < maxAvgRespTimeTestNodes.getLength(); i++) {
                Element maxAvgRespTimeTestElement = (Element) maxAvgRespTimeTestNodes.item(i);

                //TODO handle missing description
                //TODO handle empty elements

                String name = maxAvgRespTimeTestElement.getElementsByTagName("testName").item(0).getTextContent();
                String description = maxAvgRespTimeTestElement.getElementsByTagName("description").item(0).getTextContent();
                String transactionName = maxAvgRespTimeTestElement.getElementsByTagName("transactionName").item(0).getTextContent();
                long maxAvgRespTime = Long.parseLong(maxAvgRespTimeTestElement.getElementsByTagName("maxAvgRespTime").item(0).getTextContent());

                MaxAvgRespTimeTest maxAvgRespTimeTest = new MaxAvgRespTimeTest(name, description, transactionName, maxAvgRespTime);
                tests.add(maxAvgRespTimeTest);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.getMessage();
        }
    }

    public static void execute() {
        for (Test test : tests) {
            test.execute();
        }
    }

    // TODO CI-friendly exit code and logging framework output
    public static int reportResults() {
        return 0;
    }

}
