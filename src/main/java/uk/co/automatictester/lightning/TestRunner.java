package uk.co.automatictester.lightning;

import com.beust.jcommander.JCommander;
import uk.co.automatictester.lightning.params.CmdLineParams;
import uk.co.automatictester.lightning.tests.Test;

public class TestRunner {

    private static int failureCount = 0;

    public static void main(String[] args) {
        CmdLineParams params = new CmdLineParams();
        new JCommander(params, args);
        if (!params.skipSchemaValidation()) {
            XMLSchemaValidator.validate(params.getXmlFile());
        }
        TestSet.load(params.getXmlFile());
        JMeterTransactions originalJMeterTransactions = JMeterCSVFileReader.read(params.getCSVFile());
        for(Test test : TestSet.getTests()) {
            test.execute(originalJMeterTransactions);
            if (test.isFailed()) failureCount++;
            System.out.println(test.getReport());
        }
        System.out.println(TestSet.getTestSetExecutionReport());
        System.exit(TestSet.getFailureCount());
    }

}
