package uk.co.automatictester.lightning.ci;

import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestSet;

public class TeamCityReporter extends CIReporter {

    private static final String TEAMCITY_BUILD_STATUS = "%nSet TeamCity build status text:%n" +
            "##teamcity[buildStatus text='%s']";

    private static final String TEAMCITY_BUILD_PROBLEM = "%nSet TeamCity build status text:%n" +
            "##teamcity[buildProblem description='%s']";

    public void setTeamCityBuildStatusText(TestSet testSet) {
        String teamCityOutput = (testSet.getFailCount() + testSet.getIgnoreCount()) > 0 ? TEAMCITY_BUILD_PROBLEM : TEAMCITY_BUILD_STATUS;
        System.out.println(String.format(teamCityOutput, getVerifySummary(testSet)));
    }

    public void setTeamCityBuildStatusText(JMeterTransactions jmeterTransactions) {
        String teamCityOutput = jmeterTransactions.getFailCount() > 0 ? TEAMCITY_BUILD_PROBLEM : TEAMCITY_BUILD_STATUS;
        System.out.println(String.format(teamCityOutput, getReportSummary(jmeterTransactions)));
    }

}
