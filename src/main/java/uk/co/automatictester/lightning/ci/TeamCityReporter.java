package uk.co.automatictester.lightning.ci;

import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestSet;

public class TeamCityReporter extends CIReporter {

	private static final String teamCityOutputBuildStatus = "%nSet TeamCity build status text:%n" +
			"##teamcity[buildStatus text='%s']";

	private static final String teamCityOutputBuildProblem = "%nSet TeamCity build status text:%n" +
			"##teamcity[buildProblem description='%s']";

    public void setTeamCityBuildStatusText(TestSet testSet) {
		String teamCityOutput = testSet.getFailCount() + testSet.getIgnoreCount() > 0 ? teamCityOutputBuildProblem : teamCityOutputBuildStatus;
        System.out.println(String.format(teamCityOutput, getVerifySummary(testSet)));
    }

    public void setTeamCityBuildStatusText(JMeterTransactions jmeterTransactions) {
		String teamCityOutput = jmeterTransactions.getFailCount()  > 0 ? teamCityOutputBuildProblem : teamCityOutputBuildStatus;
        System.out.println(String.format(teamCityOutput, getReportSummary(jmeterTransactions)));
    }

}
