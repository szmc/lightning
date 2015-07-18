package uk.co.automatictester.lightning;

import uk.co.automatictester.lightning.tests.*;

import java.util.ArrayList;
import java.util.List;

public class TestSet {

    private List<LightningTest> tests = new ArrayList<>();
    private int passCount = 0;
    private int failureCount = 0;
    private int errorCount = 0;
    private String testSetExecutionReport = "";

    public TestSet(List<LightningTest> tests) {
        this.tests = tests;
    }

    public void execute(JMeterTransactions originalJMeterTransactions) {
        for (LightningTest test : getTests()) {
            test.execute(originalJMeterTransactions);
            if (test.isPassed()) passCount++;
            if (test.isFailed()) failureCount++;
            if (test.isError()) errorCount++;
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
                + "Test set status:   %s", getTests().size(), getPassCount(), getFailCount(), getErrorCount(), getTestSetStatus());
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

    public int getErrorCount() {
        return errorCount;
    }

    public List<LightningTest> getTests() {
        return tests;
    }

    private String getTestSetStatus() {
        return (((failureCount != 0) || (getErrorCount() != 0)) ? "FAIL" : "Pass");
    }

}
