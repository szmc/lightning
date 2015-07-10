package uk.co.automatictester.lightning.params;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CmdLineParamsTest {

    @DataProvider(name = "teamcity")
    private String[][] teamCity() {
        return new String[][]{
                {"teamcity"},
                {"TeamCity"}
        };
    }

    @DataProvider(name = "jenkins")
    private String[][] jenkins() {
        return new String[][]{
                {"jenkins"},
                {"Jenkins"}
        };
    }

    @Test(dataProvider = "teamcity")
    public void verifyIsCIEqualToTeamCityTrue(String ci) {
        CmdLineParams params = new CmdLineParams();
        params.setCI(ci);
        assertThat(params.isCIEqualTo("teamcity"), is(true));
    }

    @Test(dataProvider = "jenkins")
    public void verifyIsCIEqualToJenkinsTrue(String ci) {
        CmdLineParams params = new CmdLineParams();
        params.setCI(ci);
        assertThat(params.isCIEqualTo("jenkins"), is(true));
    }

    @Test
    public void verifyIsCIEqualToTeamCityFalse() {
        CmdLineParams params = new CmdLineParams();
        params.setCI("temacity");
        assertThat(params.isCIEqualTo("teamcity"), is(false));
    }

    @Test
    public void verifyIsCIEqualToTeamCityNotSet() {
        CmdLineParams params = new CmdLineParams();
        assertThat(params.isCIEqualTo("teamcity"), is(false));
    }
}