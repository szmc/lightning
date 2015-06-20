package uk.co.automatictester.lightning;

import com.beust.jcommander.JCommander;
import uk.co.automatictester.lightning.params.CmdLineParams;

public class TestRunner {

    public static void main(String[] args) {
        CmdLineParams params = new CmdLineParams();
        new JCommander(params, args);
        
        if (!params.skipSchemaValidation()) {
            XMLSchemaValidator.validate(params.getXmlFile());
        }

        TestSet testSet = new TestSet();
        testSet.load(params.getXmlFile());

        JMeterTransactions originalJMeterTransactions = JMeterCSVFileReader.read(params.getCSVFile());

        testSet.execute(originalJMeterTransactions);
        System.out.println(testSet.getTestSetExecutionReport());
        System.out.println(testSet.getTestSetExecutionSummaryReport());
        System.exit(testSet.getFailureCount());
    }

}
