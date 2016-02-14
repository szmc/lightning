package uk.co.automatictester.lightning;

import uk.co.automatictester.lightning.enums.TestResult;
import uk.co.automatictester.lightning.tests.LightningTest;

import java.util.ArrayList;
import java.util.List;

public class TestSet {

    private List<LightningTest> tests = new ArrayList<>();
    private int passCount = 0;
    private int failCount = 0;
    private int ignoreCount = 0;

    public TestSet(List<LightningTest> tests) {
        this.tests = tests;
    }

    public void execute(ArrayList<ArrayList<String>> dataEntires) {
        for (LightningTest test : getTests()) {
            test.execute(dataEntires);
            if (test.getResult() == TestResult.PASS) {
                passCount++;
            } else if (test.getResult() == TestResult.FAIL) {
                failCount++;
            } else if (test.getResult() == TestResult.IGNORED) {
                ignoreCount++;
            }
            test.printTestExecutionReport();
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

    public List<LightningTest> getTests() {
        return tests;
    }

}
