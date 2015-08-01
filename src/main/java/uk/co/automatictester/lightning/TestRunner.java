package uk.co.automatictester.lightning;

import uk.co.automatictester.lightning.ci.JenkinsReporter;
import uk.co.automatictester.lightning.ci.TeamCityReporter;
import uk.co.automatictester.lightning.cli.CommandLineInterface;
import uk.co.automatictester.lightning.readers.JMeterCSVFileReader;
import uk.co.automatictester.lightning.readers.LightningXMLFileReader;
import uk.co.automatictester.lightning.reporters.JMeterReporter;
import uk.co.automatictester.lightning.reporters.TestSetReporter;
import uk.co.automatictester.lightning.tests.LightningTest;

import java.util.List;

public class TestRunner {

    private static int exitCode;
    private static CommandLineInterface params;
    private static TestSet testSet;

    public static void main(String[] args) {
        parseParams(args);

        String parsedCommand = params.getParsedCommand();
        if (params.isHelpRequested()) {
            params.printHelp();
        } else if (parsedCommand.equals("verify")) {
            runTests();
            notifyCIServer();
            setExitCode();
        } else if (parsedCommand.equals("report")) {
            runReport();
        }
    }

    private static void parseParams(String[] args) {
        params = new CommandLineInterface(args);
    }

    private static void runTests() {
        long testSetExecStart = System.currentTimeMillis();

        String xmlFile = params.verify.getXmlFile();
        List<LightningTest> tests = new LightningXMLFileReader().getTests(xmlFile);
        testSet = new TestSet(tests);

        JMeterTransactions originalJMeterTransactions = new JMeterCSVFileReader().getTransactions(params.verify.getCSVFile());
        testSet.execute(originalJMeterTransactions);

        new TestSetReporter(testSet).printTestSetExecutionSummaryReport();

        long testSetExecEnd = System.currentTimeMillis();
        long testExecTime = testSetExecEnd - testSetExecStart;
        System.out.println(String.format("Execution time:    %dms", testExecTime));

        exitCode = testSet.getFailCount() + testSet.getIgnoreCount();
    }

    private static void runReport() {
        JMeterTransactions jmeterTransactions = new JMeterCSVFileReader().getTransactions(params.report.getCSVFile());
        JMeterReporter reporter = new JMeterReporter(jmeterTransactions);
        reporter.printJMeterReport();
    }

    private static void notifyCIServer() {
        if (params.verify.isCIEqualTo("teamcity")) {
            new TeamCityReporter().setTeamCityBuildStatusText(testSet);
        } else if (params.verify.isCIEqualTo("jenkins")) {
            new JenkinsReporter().setJenkinsBuildName(testSet);
        }
    }

    private static void setExitCode() {
        System.exit(exitCode);
    }

}
