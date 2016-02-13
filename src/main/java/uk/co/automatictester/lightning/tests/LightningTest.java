package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.enums.TestResult;

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
