package uk.co.automatictester.lightning;

import uk.co.automatictester.lightning.data.JMeterTransactions;
import uk.co.automatictester.lightning.reporters.RespTimeBasedTestReporter;
import uk.co.automatictester.lightning.reporters.TestReporter;
import uk.co.automatictester.lightning.tests.ClientSideTest;
import uk.co.automatictester.lightning.tests.RespTimeBasedTest;

import java.util.ArrayList;
import java.util.List;

public class TestSet {

    private List<ClientSideTest> tests = new ArrayList<>();
    private int passCount = 0;
    private int failCount = 0;
    private int ignoreCount = 0;

    public TestSet(List<ClientSideTest> tests) {
        this.tests = tests;
    }

    public void execute(JMeterTransactions originalJMeterTransactions) {
        for (ClientSideTest test : getTests()) {
            test.execute(originalJMeterTransactions);
            if (test.getResult() == TestResult.PASS) {
                passCount++;
            } else if (test.getResult() == TestResult.FAIL) {
                failCount++;
            } else if (test.getResult() == TestResult.IGNORED) {
                ignoreCount++;
            }
            if (test instanceof RespTimeBasedTest) {
                new RespTimeBasedTestReporter((RespTimeBasedTest) test).printTestExecutionReport();
            } else {
                new TestReporter(test).printTestExecutionReport();
            }
        }
    }

    public int getTestCount() {
        return tests.size();
    }

    public int getPassCount() {
        return passCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public int getIgnoreCount() {
        return ignoreCount;
    }

    public List<ClientSideTest> getTests() {
        return tests;
    }

}
