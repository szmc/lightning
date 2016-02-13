package uk.co.automatictester.lightning.runners;

import uk.co.automatictester.lightning.TestSet;
import uk.co.automatictester.lightning.ci.JenkinsReporter;
import uk.co.automatictester.lightning.ci.TeamCityReporter;
import uk.co.automatictester.lightning.cli.CommandLineInterface;
import uk.co.automatictester.lightning.data.JMeterTransactions;
import uk.co.automatictester.lightning.readers.JMeterCSVFileReader;
import uk.co.automatictester.lightning.readers.LightningXMLFileReader;
import uk.co.automatictester.lightning.reporters.JMeterReporter;
import uk.co.automatictester.lightning.reporters.TestSetReporter;
import uk.co.automatictester.lightning.tests.ClientSideTest;

import java.util.List;

public class CliTestRunner {

    private static int exitCode = 0;
    private static CommandLineInterface params;
    private static TestSet testSet;
    private static JMeterTransactions jmeterTransactions;
    private static String mode;

    public static void main(String[] args) {
        parseParams(args);

        mode = params.getParsedCommand();
        if (params.isHelpRequested()) {
            params.printHelp();
        } else if (mode.equals("verify")) {
            runTests();
            notifyCIServer();
            setExitCode();
        } else if (mode.equals("report")) {
            runReport();
            notifyCIServer();
            setExitCode();
        }
    }

    private static void parseParams(String[] args) {
        params = new CommandLineInterface(args);
    }

    private static void runTests() {
        long testSetExecStart = System.currentTimeMillis();

        String xmlFile = params.verify.getXmlFile();
        List<ClientSideTest> tests = new LightningXMLFileReader().getTests(xmlFile);
        testSet = new TestSet(tests);

        jmeterTransactions = new JMeterCSVFileReader().getTransactions(params.verify.getCSVFile());
        testSet.execute(jmeterTransactions);

        new TestSetReporter(testSet).printTestSetExecutionSummaryReport();

        long testSetExecEnd = System.currentTimeMillis();
        long testExecTime = testSetExecEnd - testSetExecStart;
        System.out.println(String.format("Execution time:    %dms", testExecTime));

        if (testSet.getFailCount() + testSet.getIgnoreCount() != 0) {
            exitCode = 1;
        }
    }

    private static void runReport() {
        jmeterTransactions = new JMeterCSVFileReader().getTransactions(params.report.getCSVFile());
        JMeterReporter reporter = new JMeterReporter(jmeterTransactions);
        reporter.printJMeterReport();
        if (jmeterTransactions.getFailCount() != 0) {
            exitCode = 2;
        }
    }

    private static void notifyCIServer() {
        if (mode.equals("verify")) {
            if (params.verify.isCIEqualTo("teamcity")) {
                new TeamCityReporter().setTeamCityBuildStatusText(testSet);
            } else if (params.verify.isCIEqualTo("jenkins")) {
                new JenkinsReporter().setJenkinsBuildName(testSet);
            }
        } else if (mode.equals("report")) {
            if (params.report.isCIEqualTo("teamcity")) {
                new TeamCityReporter().setTeamCityBuildStatusText(jmeterTransactions);
            } else if (params.report.isCIEqualTo("jenkins")) {
                new JenkinsReporter().setJenkinsBuildName(jmeterTransactions);
            }
        }
    }

    private static void setExitCode() {
        System.exit(exitCode);
    }

}
