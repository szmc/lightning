package uk.co.automatictester.lightning;

public class TestResult {

    private String testName;
    private String testDescription;
    private String expectedResult;
    private String actualResult;
    private boolean failed;

    public TestResult(String testName, String testDescription,
                      String expectedResult, String actualResult,
                      boolean failed) {
        this.testName = testName;
        this.testDescription = testDescription;
        this.expectedResult = expectedResult;
        this.actualResult = actualResult;
        this.failed = failed;
    }

    public void report() {
        System.out.println("Test name:        " + testName);
        System.out.println("Test description: " + testDescription);
        System.out.println("Expected result:  " + expectedResult);
        System.out.println("Actual result:    " + actualResult);
        System.out.println("Test result:      " + ((failed) ? "FAILED" : "Pass") + System.lineSeparator());
    }
}
