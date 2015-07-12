package uk.co.automatictester.lightning;

import com.beust.jcommander.JCommander;
import uk.co.automatictester.lightning.params.CmdLineParams;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

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
        setJenkinsBuildNameIfRequested();
        setExitCode();
    }

    public static void runTests() {
        long testSetExecStart = System.currentTimeMillis();

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
            printHelpIfRequested();
            System.exit(-1);
        }
    }

    public static void printHelpIfRequested() {
        if (params.isHelp()) {
            jc.setProgramName("java -jar lightning-<version_number>.jar");
            jc.usage();
        }
    }

    public static void setTeamCityBuildStatusTextIfRequested() {
        if (params.isCIEqualTo("teamcity")) {
            System.out.println(System.lineSeparator() + "Set TeamCity build status text:");
            System.out.println(String.format("##teamcity[buildStatus text='%s']", getExecutionSummaryForCI()));
        }
    }

    public static void setJenkinsBuildNameIfRequested() {
        if (params.isCIEqualTo("jenkins")) {
            try {
                Properties props = new Properties();
                props.setProperty("result.string", getExecutionSummaryForCI());
                File f = new File("lightning-jenkins.properties");
                OutputStream out = new FileOutputStream(f);
                props.store(out, "In Jenkins Build Name Setter Plugin, define build name as: ${PROPFILE,file=\"lightning-jenkins.properties\",property=\"result.string\"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getExecutionSummaryForCI() {
        int executed = testSet.getTests().size();
        int failed = testSet.getFailCount();
        int ignored = testSet.getErrorCount();
        return String.format("Tests executed: %s, failed: %s, ignored: %s", executed, failed, ignored);
    }

    private static void setExitCode() {
        System.exit(exitCode);
    }

}
