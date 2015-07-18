package uk.co.automatictester.lightning.ci;

import uk.co.automatictester.lightning.TestSet;

public class TeamCityReporter extends CIReporter {

    public void setTeamCityBuildStatusText(TestSet testSet) {
        String teamCityOutput = "%nSet TeamCity build status text:%n" +
                "##teamcity[buildStatus text='%s']";

        System.out.println(String.format(teamCityOutput, getExecutionSummary(testSet)));
    }

}
