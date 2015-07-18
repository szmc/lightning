package uk.co.automatictester.lightning;

import uk.co.automatictester.lightning.ci.JenkinsReporter;
import uk.co.automatictester.lightning.ci.TeamCityReporter;
import uk.co.automatictester.lightning.params.CommandLineParameters;
import uk.co.automatictester.lightning.tests.LightningTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

public class TestRunner {

    private static int exitCode;
    private static CommandLineParameters params;
    private static TestSet testSet;

    public static void main(String[] args) {
        parseParams(args);
        printHelpAndExitIfRequested();
        runTests();
        notifyCIServer();
        setExitCode();
    }

    private static void parseParams(String[] args) {
        params = new CommandLineParameters(args);
    }

    private static void printHelpAndExitIfRequested() {
        if (params.isHelpRequested()) {
            params.printHelp();
            System.exit(1);
        }
    }

    private static void runTests() {
        long testSetExecStart = System.currentTimeMillis();

        String xmlFile = params.getXmlFile();
        List<LightningTest> tests = new LightningXMLFileReader().getTests(xmlFile);
        testSet = new TestSet(tests);

        JMeterTransactions originalJMeterTransactions = new JMeterCSVFileReader().getTransactions(params.getCSVFile());
        testSet.execute(originalJMeterTransactions);

        System.out.println(testSet.getTestSetExecutionReport());
        System.out.println(testSet.getTestSetExecutionSummaryReport());

        long testSetExecEnd = System.currentTimeMillis();
        long testExecTime = testSetExecEnd - testSetExecStart;
        System.out.println(String.format("Execution time:    %dms", testExecTime));

        exitCode = testSet.getFailCount() + testSet.getErrorCount();
    }

    private static void notifyCIServer() {
        if (params.isCIEqualTo("teamcity")) {
            new TeamCityReporter().setTeamCityBuildStatusText(testSet);
        } else if(params.isCIEqualTo("jenkins")) {
            new JenkinsReporter().setJenkinsBuildName(testSet);
        }
    }

    private static void setExitCode() {
        System.exit(exitCode);
    }

}
