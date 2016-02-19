package uk.co.automatictester.lightning.runners;

import uk.co.automatictester.lightning.TestSet;
import uk.co.automatictester.lightning.ci.JenkinsReporter;
import uk.co.automatictester.lightning.ci.TeamCityReporter;
import uk.co.automatictester.lightning.cli.CommandLineInterface;
import uk.co.automatictester.lightning.data.JMeterTransactions;
import uk.co.automatictester.lightning.data.PerfMonDataEntries;
import uk.co.automatictester.lightning.readers.JMeterCSVFileReader;
import uk.co.automatictester.lightning.readers.LightningXMLFileReader;
import uk.co.automatictester.lightning.readers.PerfMonDataReader;
import uk.co.automatictester.lightning.reporters.JMeterReporter;
import uk.co.automatictester.lightning.reporters.TestSetReporter;
import uk.co.automatictester.lightning.tests.ClientSideTest;
import uk.co.automatictester.lightning.tests.ServerSideTest;

import java.util.List;

public class CliTestRunner {

    private static int exitCode = 0;
    private static CommandLineInterface params;
    private static TestSet testSet;
    private static JMeterTransactions jmeterTransactions;
    private static PerfMonDataEntries perfMonDataEntries;
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
        String jmeterCsvFile = params.verify.getJmeterCsvFile();
        String perfmonCsvFile = params.verify.getPerfmonCsvFile();

        LightningXMLFileReader xmlFileReader = new LightningXMLFileReader();
        xmlFileReader.readTests(xmlFile);

        List<ClientSideTest> clientSideTests = xmlFileReader.getClientSideTests();
        List<ServerSideTest> serverSideTests = xmlFileReader.getServerSideTests();

        testSet = new TestSet(clientSideTests, serverSideTests);

        jmeterTransactions = new JMeterCSVFileReader().getTransactions(jmeterCsvFile);

        if (params.verify.isPerfmonCsvFileProvided()) {
            perfMonDataEntries = new PerfMonDataReader().getDataEntires(perfmonCsvFile);
            testSet.executeServerSideTests(perfMonDataEntries);
        }

        testSet.executeClientSideTests(jmeterTransactions);

        new TestSetReporter(testSet).printTestSetExecutionSummaryReport();

        long testSetExecEnd = System.currentTimeMillis();
        long testExecTime = testSetExecEnd - testSetExecStart;
        System.out.println(String.format("Execution time:    %dms", testExecTime));

        if (testSet.getFailCount() + testSet.getIgnoreCount() != 0) {
            exitCode = 1;
        }
    }

    private static void runReport() {
        jmeterTransactions = new JMeterCSVFileReader().getTransactions(params.report.getJmeterCsvFile());
        JMeterReporter reporter = new JMeterReporter(jmeterTransactions);
        reporter.printJMeterReport();
        if (jmeterTransactions.getFailCount() != 0) {
            exitCode = 2;
        }
    }

    private static void notifyCIServer() {
        if (mode.equals("verify")) {
            if (params.verify.isCiEqualTo("teamcity")) {
                new TeamCityReporter().setTeamCityBuildStatusText(testSet);
            } else if (params.verify.isCiEqualTo("jenkins")) {
                new JenkinsReporter().setJenkinsBuildName(testSet);
            }
        } else if (mode.equals("report")) {
            if (params.report.isCiEqualTo("teamcity")) {
                new TeamCityReporter().setTeamCityBuildStatusText(jmeterTransactions);
            } else if (params.report.isCiEqualTo("jenkins")) {
                new JenkinsReporter().setJenkinsBuildName(jmeterTransactions);
            }
        }
    }

    private static void setExitCode() {
        System.exit(exitCode);
    }

}
