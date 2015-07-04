package uk.co.automatictester.lightning;

import com.beust.jcommander.JCommander;
import uk.co.automatictester.lightning.params.CmdLineParams;

public class TestRunner {

    private static int exitCode;
    private static JCommander jc;
    private static CmdLineParams params;

    public static void main(String[] args) {
        parseParams(args);
        printHelpAndExitIfRequested();
        runTests();
        setExitCode();
    }

    private static void runTests() {
        long testSetExecStart = System.currentTimeMillis();

        if (!params.skipSchemaValidation()) {
            new XMLSchemaValidator().validate(params.getXmlFile());
        }

        TestSet testSet = new TestSet();
        testSet.load(params.getXmlFile());

        JMeterTransactions originalJMeterTransactions = new JMeterCSVFileReader().read(params.getCSVFile());
        testSet.execute(originalJMeterTransactions);

        System.out.println(testSet.getTestSetExecutionReport());
        System.out.println(testSet.getTestSetExecutionSummaryReport());

        long testSetExecEnd = System.currentTimeMillis();
        long testExecTime = testSetExecEnd - testSetExecStart;
        System.out.println(String.format("Execution time:    %dms", testExecTime));

        exitCode = testSet.getFailCount() + testSet.getErrorCount();
    }

    private static void parseParams(String[] args) {
        params = new CmdLineParams();
        jc = new JCommander(params, args);
    }

    private static void printHelpAndExitIfRequested() {
        if (params.isHelp()) {
            jc.setProgramName("java -jar lightning-<version_number>.jar");
            jc.usage();
            System.exit(-1);
        }
    }

    private static void setExitCode() {
        System.exit(exitCode);
    }

}
