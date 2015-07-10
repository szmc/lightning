package uk.co.automatictester.lightning;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static uk.co.automatictester.lightning.data.TestData.CSV_10_TRANSACTIONS;
import static uk.co.automatictester.lightning.data.TestData.TEST_SET_4_0_0;

public class TestRunnerTest extends ConsoleOutputTest {

    @Test
    public void verifyTeamCityOutputPresentWhenExpected() {
        String expectedOutput = String.format("Set TeamCity build status text:%n" +
                "##teamcity[buildStatus text='Tests executed: 4, failed: 0, ignored: 0']");

        String[] cmdLineParams = new String[]{"-xml=" + TEST_SET_4_0_0, "-csv=" + CSV_10_TRANSACTIONS, "-ci=teamcity"};
        TestRunner.parseParams(cmdLineParams);
        TestRunner.runTests();
        TestRunner.setTeamCityBuildStatusTextIfRequested();
        assertThat(outContent.toString(), containsString(expectedOutput));
    }

    @Test
    public void verifyTeamCityOutputNotPresentWhenNotExpected() {
        String unexpectedOutput = String.format("Set TeamCity build status text:%n" +
                "##teamcity[buildStatus text='Tests executed: 4, failed: 0, ignored: 0']");

        String[] cmdLineParams = new String[]{"-xml=" + TEST_SET_4_0_0, "-csv=" + CSV_10_TRANSACTIONS};
        TestRunner.parseParams(cmdLineParams);
        TestRunner.runTests();
        TestRunner.setTeamCityBuildStatusTextIfRequested();
        assertThat(outContent.toString(), not(containsString(unexpectedOutput)));
    }
}