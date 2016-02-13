package uk.co.automatictester.lightning.tests;

import uk.co.automatictester.lightning.TestResult;
import uk.co.automatictester.lightning.data.PerfMonDataEntries;

public abstract class ServerSideTest {

    protected final String name;
    protected final String description;
    protected final String hostAndMetric;
    protected final String type;
    protected String expectedResult;
    protected String actualResult;
    protected TestResult result;
    protected int dataEntriesCount;

    protected ServerSideTest(String name, String type, String description, String hostAndMetric) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.hostAndMetric = hostAndMetric;
        this.expectedResult = "";
        this.actualResult = "";
        this.result = null;
    }

    public abstract void execute(PerfMonDataEntries perfMonDataEntries);

    public PerfMonDataEntries filterDataEntries(PerfMonDataEntries originalPerfMonDataEntries) {
        if (getHostAndMetric() != null) {
            return originalPerfMonDataEntries.excludeHostAndMetricOtherThan(getHostAndMetric());
        } else {
            return originalPerfMonDataEntries;
        }
    }

    public String getName() {
        return name;
    }

    public int getDataEntriesCount() {
        return dataEntriesCount;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getHostAndMetric() {
        return hostAndMetric;
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
