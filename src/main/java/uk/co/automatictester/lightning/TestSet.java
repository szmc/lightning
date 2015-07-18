package uk.co.automatictester.lightning;

import uk.co.automatictester.lightning.tests.LightningTest;

import java.util.ArrayList;
import java.util.List;

public class TestSet {

    private List<LightningTest> tests = new ArrayList<>();
    private int passCount = 0;
    private int failureCount = 0;
    private int ignoreCount = 0;
    private String testSetExecutionReport = "";

    public TestSet(List<LightningTest> tests) {
        this.tests = tests;
    }

    public void execute(JMeterTransactions originalJMeterTransactions) {
        for (LightningTest test : getTests()) {
            test.execute(originalJMeterTransactions);
            if (test.getResult() == TestResult.PASS) passCount++;
            if (test.getResult() == TestResult.FAIL) failureCount++;
            if (test.getResult() == TestResult.IGNORED) ignoreCount++;
            testSetExecutionReport += test.getReport();
        }
    }

    public String getTestSetExecutionReport() {
        return testSetExecutionReport;
    }

    public String getTestSetExecutionSummaryReport() {
        return String.format("============= EXECUTION SUMMARY =============%n"
                + "Tests executed:    %s%n"
                + "Tests passed:      %s%n"
                + "Tests failed:      %s%n"
                + "Tests with errors: %s%n"
                + "Test set status:   %s", getTests().size(), getPassCount(), getFailCount(), getIgnoreCount(), getTestSetStatus());
    }

    public int getTestCount() {
        return tests.size();
    }

    public int getPassCount() {
        return passCount;
    }

    public int getFailCount() {
        return failureCount;
    }

    public int getIgnoreCount() {
        return ignoreCount;
    }

    public List<LightningTest> getTests() {
        return tests;
    }

    private String getTestSetStatus() {
        return (((failureCount != 0) || (getIgnoreCount() != 0)) ? "FAIL" : "Pass");
    }

}
