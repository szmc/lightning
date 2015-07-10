package uk.co.automatictester.lightning;

import com.beust.jcommander.JCommander;
import uk.co.automatictester.lightning.params.CmdLineParams;

public class TestRunner {

    private static int exitCode;
    private static JCommander jc;
    private static CmdLineParams params;
    private static TestSet testSet;

    public static void main(String[] args) {
        parseParams(args);
        printHelpAndExitIfRequested();
        runTests();
        setTeamCityBuildStatusTextIfRequested();
        setExitCode();
    }

    public static void runTests() {
        long testSetExecStart = System.currentTimeMillis();

        if (!params.skipSchemaValidation()) {
            new XMLSchemaValidator().validate(params.getXmlFile());
        }

        testSet = new TestSet();
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

    public static void parseParams(String[] args) {
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

    public static void setTeamCityBuildStatusTextIfRequested() {
        if (params.isCIEqualToTeamCity()) {
            int executed = testSet.getTests().size();
            int failed = testSet.getFailCount();
            int ignored = testSet.getErrorCount();
            System.out.println(System.lineSeparator() + "Set TeamCity build status text:");
            System.out.println(String.format("##teamcity[buildStatus text='Tests executed: %s, failed: %s, ignored: %s']", executed, failed, ignored));
        }
    }

    private static void setExitCode() {
        System.exit(exitCode);
    }

}
