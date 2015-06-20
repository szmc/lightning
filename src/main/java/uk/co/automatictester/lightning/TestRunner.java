package uk.co.automatictester.lightning;

import com.beust.jcommander.JCommander;
import uk.co.automatictester.lightning.params.CmdLineParams;
import uk.co.automatictester.lightning.tests.Test;

public class TestRunner {

    public static void main(String[] args) {
        CmdLineParams params = new CmdLineParams();
        new JCommander(params, args);
        if (!params.skipSchemaValidation()) {
            XMLSchemaValidator.validate(params.getXmlFile());
        }
        TestSet.load(params.getXmlFile());
        JMeterTransactions originalJMeterTransactions = JMeterCSVFileReader.read(params.getCSVFile());
        TestSet.execute(originalJMeterTransactions);
        System.out.println(TestSet.getTestSetExecutionReport());
        System.exit(TestSet.getFailureCount());
    }

}
