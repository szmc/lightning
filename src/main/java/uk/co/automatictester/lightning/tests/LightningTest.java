package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.enums.TestResult;

import java.util.ArrayList;

public abstract class LightningTest {

    protected final String name;
    protected final String description;
    protected final String type;
    protected String expectedResult;
    protected String actualResult;
    protected TestResult result;

    protected LightningTest(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.expectedResult = "";
        this.actualResult = "";
        this.result = null;
    }

    public abstract void printTestExecutionReport();

    public abstract void execute(ArrayList<ArrayList<String>> dataEntries);

    protected String getDescriptionForReport() {
        return (!getDescription().isEmpty()) ? (String.format("Test description:     %s%n", getDescription())) : "";
    }

    protected String getResultForReport() {
        if (getResult() == TestResult.IGNORED) {
            return "IGNORED";
        } else if (getResult() == TestResult.FAIL) {
            return "FAIL";
        } else {
            return "Pass";
        }
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getActualResult() {
        return actualResult;
    }

    public TestResult getResult() {
        return result;
    }
}
