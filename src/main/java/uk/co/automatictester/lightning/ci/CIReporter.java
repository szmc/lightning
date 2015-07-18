package uk.co.automatictester.lightning.ci;

import uk.co.automatictester.lightning.TestSet;

public abstract class CIReporter {

    public static String getExecutionSummary(TestSet testSet) {
        int executed = testSet.getTestCount();
        int failed = testSet.getFailCount();
        int ignored = testSet.getErrorCount();
        return String.format("Tests executed: %s, failed: %s, ignored: %s", executed, failed, ignored);
    }

}
