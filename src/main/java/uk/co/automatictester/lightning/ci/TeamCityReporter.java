package uk.co.automatictester.lightning.ci;

import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestSet;

public class TeamCityReporter extends CIReporter {

    private static final String teamCityOutput = "%nSet TeamCity build status text:%n" +
            "##teamcity[buildStatus text='%s']";

    public void setTeamCityBuildStatusText(TestSet testSet) {
        System.out.println(String.format(teamCityOutput, getVerifySummary(testSet)));
    }

    public void setTeamCityBuildStatusText(JMeterTransactions jmeterTransactions) {
        System.out.println(String.format(teamCityOutput, getReportSummary(jmeterTransactions)));
    }

}
