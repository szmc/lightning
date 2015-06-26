package uk.co.automatictester.lightning;

import com.beust.jcommander.JCommander;
import uk.co.automatictester.lightning.params.CmdLineParams;

public class TestRunner {

    private static int exitCode;

    public static void main(String[] args) {
        runTests(args);
        setExitCode();
    }

    public static void runTests(String[] args) {
        CmdLineParams params = new CmdLineParams();
        new JCommander(params, args);

        if (!params.skipSchemaValidation()) {
            new XMLSchemaValidator().validate(params.getXmlFile());
        }

        TestSet testSet = new TestSet();
        testSet.load(params.getXmlFile());

        JMeterTransactions originalJMeterTransactions = JMeterCSVFileReader.read(params.getCSVFile());
        testSet.execute(originalJMeterTransactions);

        System.out.println(testSet.getTestSetExecutionReport());
        System.out.println(testSet.getTestSetExecutionSummaryReport());

        exitCode = testSet.getFailCount();
    }

    private static void setExitCode() {
        System.exit(exitCode);
    }

}
