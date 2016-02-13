package uk.co.automatictester.lightning.tests;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import uk.co.automatictester.lightning.enums.TestResult;
import uk.co.automatictester.lightning.data.PerfMonDataEntries;
import uk.co.automatictester.lightning.enums.ServerSideTestType;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ServerSideTest {

    private static final String GREATER_THAN_MESSAGE = "Average value > %s";
    private static final String LESS_THAN_MESSAGE = "Average value < %s";
    private static final String BETWEEN_MESSAGE = "Average value between %s and %s";
    private static final String ACTUAL_RESULT_MESSAGE = "Average value = %s";

    private final String name;
    private final String description;
    private final String hostAndMetric;
    private final String type;
    private final ServerSideTestType subtype;
    private String expectedResult;
    private String actualResult;
    private TestResult result;
    private int dataEntriesCount;
    private final long avgRespTimeA;
    private long avgRespTimeB;
    private String expectedResultMessage;

    public ServerSideTest(String name, String type, ServerSideTestType subtype, String description, String hostAndMetric, long avgRespTimeA, long avgRespTimeB) {
        this(name, type, subtype, description, hostAndMetric, avgRespTimeA);
        this.avgRespTimeB = avgRespTimeB;
    }

    public ServerSideTest(String name, String type, ServerSideTestType subtype, String description, String hostAndMetric, long avgRespTimeA) {
        this.name = name;
        this.type = type;
        this.subtype = subtype;
        this.description = description;
        this.hostAndMetric = hostAndMetric;
        this.expectedResult = "";
        this.actualResult = "";
        this.result = null;
        this.avgRespTimeA = avgRespTimeA;
        this.expectedResultMessage = getExpectedResultMessage();
    }

    public void execute(PerfMonDataEntries originalDataEntries) {
        Locale.setDefault(Locale.ENGLISH);

        try {
            PerfMonDataEntries dataEntries = filterDataEntries(originalDataEntries);
            dataEntriesCount = dataEntries.getDataEntriesCount();

            DescriptiveStatistics ds = new DescriptiveStatistics();
            for (List<String> transaction : dataEntries) {
                String elapsed = transaction.get(1);
                ds.addValue(Double.parseDouble(elapsed));
            }
            double avgRespTime = ds.getMean();

            DecimalFormat df = new DecimalFormat("#.##");
            double roundedAvgRespTime = Double.valueOf(df.format(avgRespTime));

            actualResult = String.format(ACTUAL_RESULT_MESSAGE, roundedAvgRespTime);

            if (subtype.equals(ServerSideTestType.GREATER_THAN)) {
                expectedResult = String.format(expectedResultMessage, avgRespTimeA);
                if (roundedAvgRespTime > avgRespTimeA) {
                    result = TestResult.PASS;
                } else {
                    result = TestResult.FAIL;
                }
            } else if (subtype.equals(ServerSideTestType.LESS_THAN)) {
                expectedResult = String.format(expectedResultMessage, avgRespTimeA);
                if (roundedAvgRespTime < avgRespTimeA) {
                    result = TestResult.PASS;
                } else {
                    result = TestResult.FAIL;
                }
            } else if (subtype.equals(ServerSideTestType.BETWEEN)) {
                expectedResult = String.format(expectedResultMessage, avgRespTimeA, avgRespTimeB);
                if ((roundedAvgRespTime > avgRespTimeA) && (roundedAvgRespTime < avgRespTimeB)) {
                    result = TestResult.PASS;
                } else {
                    result = TestResult.FAIL;
                }
            }

        } catch (Exception e) {
            result = TestResult.IGNORED;
            actualResult = e.getMessage();
        }
    }

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

    public boolean equals(Object obj) {
        if (obj instanceof ServerSideTest) {
            ServerSideTest test = (ServerSideTest) obj;
            return name.equals(test.name) &&
                    description.equals(test.description) &&
                    hostAndMetric.equals(test.hostAndMetric) &&
                    type.equals(test.type) &&
                    subtype.equals(test.subtype) &&
                    expectedResult.equals(test.expectedResult) &&
                    actualResult.equals(test.actualResult) &&
                    Objects.equals(result, test.result) &&
                    dataEntriesCount == test.dataEntriesCount &&
                    avgRespTimeA == test.avgRespTimeA &&
                    Objects.equals(avgRespTimeB, test.avgRespTimeB);
        } else {
            return false;
        }
    }

    private String getExpectedResultMessage() {
        if (subtype.equals(ServerSideTestType.GREATER_THAN)) {
            return GREATER_THAN_MESSAGE;
        } else if (subtype.equals(ServerSideTestType.BETWEEN)) {
            return BETWEEN_MESSAGE;
        } else {
            return LESS_THAN_MESSAGE;
        }
    }
}
