package uk.co.automatictester.lightning.reporters;

import uk.co.automatictester.lightning.TestSet;

public class TestSetReporter {

    private TestSet testSet;

    public TestSetReporter(TestSet testSet) {
        this.testSet = testSet;
    }

    public void printTestSetExecutionSummaryReport() {
        String summaryReport = String.format("%n============= EXECUTION SUMMARY =============%n"
                + "Tests executed:    %s%n"
                + "Tests passed:      %s%n"
                + "Tests failed:      %s%n"
                + "Tests ignored:     %s%n"
                + "Test set status:   %s", testSet.getTestCount(), testSet.getPassCount(), testSet.getFailCount(), testSet.getIgnoreCount(), getTestSetStatus());
        System.out.println(summaryReport);
    }

    private String getTestSetStatus() {
        return (((testSet.getFailCount() != 0) || (testSet.getIgnoreCount() != 0)) ? "FAIL" : "Pass");
    }

}
