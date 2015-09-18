package uk.co.automatictester.lightning.runners;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.ConsoleOutputTest;
import uk.co.automatictester.lightning.TestSet;
import uk.co.automatictester.lightning.enums.CIServer;
import uk.co.automatictester.lightning.enums.Mode;
import uk.co.automatictester.lightning.tests.LightningTest;
import uk.co.automatictester.lightning.tests.PassedTransactionsTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ApiTestRunnerTest extends ConsoleOutputTest {

    private static final String CSV_2_TRANSACTIONS_1_FAILED = "src/test/resources/csv/2_transactions_1_failed.csv";

    @Test
    public void testRun_report() {
        ApiTestRunner testRunner = new ApiTestRunner(Mode.REPORT, CSV_2_TRANSACTIONS_1_FAILED);
        configureStream();
        testRunner.run();
        assertThat(out.toString(), containsString("Transactions executed: 2, failed: 1"));
        assertThat(testRunner.getExitCode(), equalTo(2));
        revertStream();
    }

    @Test
    public void testRun_reportCiServerTeamCity() {
        ApiTestRunner testRunner = new ApiTestRunner(Mode.REPORT, CSV_2_TRANSACTIONS_1_FAILED);
        testRunner.setCiServer(CIServer.TEAMCITY);
        configureStream();
        testRunner.run();
        assertThat(out.toString(), containsString("##teamcity[buildStatus text='Transactions executed: 2, failed: 1']"));
        revertStream();
    }

    @Test
    public void testRun_verify() {
        PassedTransactionsTest passedTransactionsTest = new PassedTransactionsTest("Test #1", "passedTransactionsTest", "Verify number of passed tests", "Search", 0);
        List<LightningTest> tests = new ArrayList<>();
        tests.add(passedTransactionsTest);
        TestSet testSet = new TestSet(tests);
        ApiTestRunner testRunner = new ApiTestRunner(Mode.VERIFY, CSV_2_TRANSACTIONS_1_FAILED, testSet);
        configureStream();
        testRunner.run();
        assertThat(testRunner.getExitCode(), equalTo(1));
        revertStream();
    }

    @Test
    public void testRun_verifyCiServerTeamCity() {
        PassedTransactionsTest passedTransactionsTest = new PassedTransactionsTest("Test #1", "passedTransactionsTest", "Verify number of passed tests", "Search", 0);
        List<LightningTest> tests = new ArrayList<>();
        tests.add(passedTransactionsTest);
        TestSet testSet = new TestSet(tests);
        ApiTestRunner testRunner = new ApiTestRunner(Mode.VERIFY, CSV_2_TRANSACTIONS_1_FAILED, testSet);
        testRunner.setCiServer(CIServer.TEAMCITY);
        configureStream();
        testRunner.run();
        assertThat(testRunner.getExitCode(), equalTo(1));
        assertThat(out.toString(), containsString("##teamcity[buildStatus text='Tests executed: 1, failed: 1, ignored: 0']"));
        revertStream();
    }

}