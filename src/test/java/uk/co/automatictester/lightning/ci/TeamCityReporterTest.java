package uk.co.automatictester.lightning.ci;

import org.testng.annotations.Test;
import uk.co.automatictester.lightning.ConsoleOutputTest;
import uk.co.automatictester.lightning.TestSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeamCityReporterTest extends ConsoleOutputTest {

    @Test
    public void testSetTeamCityBuildStatusText() {
        String expectedOutput = String.format("%nSet TeamCity build status text:%n" +
                "##teamcity[buildStatus text='Tests executed: 6, failed: 2, ignored: 1']%n");

        TestSet testSet = mock(TestSet.class);
        when(testSet.getTestCount()).thenReturn(6);
        when(testSet.getFailCount()).thenReturn(2);
        when(testSet.getErrorCount()).thenReturn(1);

        new TeamCityReporter().setTeamCityBuildStatusText(testSet);
        assertThat(out.toString(), equalTo(expectedOutput));
    }
}