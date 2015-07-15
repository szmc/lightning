package uk.co.automatictester.lightning;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static uk.co.automatictester.lightning.data.TestData.CSV_10_TRANSACTIONS;
import static uk.co.automatictester.lightning.data.TestData.TEST_SET_3_0_0;

public class TestRunnerTest extends ConsoleOutputTest {

    private static final File LIGHTNING_FILE = new File("lightning-jenkins.properties");

    @Test
    public void verifyHelpOutputPresentWhenExpected() {
        String expectedOutput = String.format("Usage: java -jar lightning-<version_number>.jar [options]%n" +
                "  Options:%n" +
                "    -ci%n" +
                "       CI server (jenkins or teamcity)%n" +
                "  * -csv%n" +
                "       JMeter CSV result file%n" +
                "  * -xml%n" +
                "       Lightning XML config file");

        String[] cmdLineParams = new String[]{"-h"};
        TestRunner.parseParams(cmdLineParams);
        TestRunner.printHelpIfRequested();
        assertThat(outContent.toString(), containsString(expectedOutput));
    }

    @Test
    public void verifyHelpOutputNotPresentWhenNotExpected() {
        String unexpectedOutput = String.format("Usage: java -jar lightning-<version_number>.jar [options]%n" +
                "  Options:%n" +
                "    -ci%n" +
                "       CI server (jenkins or teamcity)%n" +
                "  * -csv%n" +
                "       JMeter CSV result file%n" +
                "  * -xml%n" +
                "       Lightning XML config file");

        String[] cmdLineParams = new String[]{"-xml=" + TEST_SET_3_0_0, "-csv=" + CSV_10_TRANSACTIONS};
        TestRunner.parseParams(cmdLineParams);
        TestRunner.printHelpIfRequested();
        assertThat(outContent.toString(), not(containsString(unexpectedOutput)));
    }

    @Test
    public void verifyTeamCityOutputPresentWhenExpected() {
        String expectedOutput = String.format("Set TeamCity build status text:%n" +
                "##teamcity[buildStatus text='Tests executed: 3, failed: 0, ignored: 0']");

        String[] cmdLineParams = new String[]{"-xml=" + TEST_SET_3_0_0, "-csv=" + CSV_10_TRANSACTIONS, "-ci=teamcity"};
        TestRunner.parseParams(cmdLineParams);
        TestRunner.runTests();
        TestRunner.setTeamCityBuildStatusTextIfRequested();
        assertThat(outContent.toString(), containsString(expectedOutput));
    }

    @Test
    public void verifyTeamCityOutputNotPresentWhenNotExpected() {
        String unexpectedOutput = String.format("Set TeamCity build status text:%n" +
                "##teamcity[buildStatus text='Tests executed: 3, failed: 0, ignored: 0']");

        String[] cmdLineParams = new String[]{"-xml=" + TEST_SET_3_0_0, "-csv=" + CSV_10_TRANSACTIONS};
        TestRunner.parseParams(cmdLineParams);
        TestRunner.runTests();
        TestRunner.setTeamCityBuildStatusTextIfRequested();
        assertThat(outContent.toString(), not(containsString(unexpectedOutput)));
    }

    @Test
    public void verifyJenkinsFilePresentWhenExpected() throws FileNotFoundException {
        String[] cmdLineParams = new String[]{"-xml=" + TEST_SET_3_0_0, "-csv=" + CSV_10_TRANSACTIONS, "-ci=jenkins"};
        TestRunner.parseParams(cmdLineParams);
        TestRunner.runTests();
        TestRunner.setJenkinsBuildNameIfRequested();
        String text = new Scanner(LIGHTNING_FILE).useDelimiter("\\A").next();
        assertThat(text, containsString("result.string=Tests executed\\: 3, failed\\: 0, ignored\\: 0"));
        LIGHTNING_FILE.delete();
    }

    @Test
    public void verifyJenkinsFileNotPresentWhenNotExpected() throws FileNotFoundException {
        LIGHTNING_FILE.delete();
        String[] cmdLineParams = new String[]{"-xml=" + TEST_SET_3_0_0, "-csv=" + CSV_10_TRANSACTIONS};
        TestRunner.parseParams(cmdLineParams);
        TestRunner.runTests();
        TestRunner.setJenkinsBuildNameIfRequested();
        assertThat(new File("lightning-jenkins.properties").exists(), is(false));
    }
}