package uk.co.automatictester.lightning.params;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CmdLineParamsTest {

    @DataProvider(name = "testData")
    private String[][] testData() {
        return new String[][]{
                {"teamcity"},
                {"TeamCity"}
        };
    }

    @Test(dataProvider = "testData")
    public void verifyIsCIEqualToTeamCityTrue(String ci) {
        CmdLineParams params = new CmdLineParams();
        params.setCI(ci);
        assertThat(params.isCIEqualToTeamCity(), is(true));
    }

    @Test
    public void verifyIsCIEqualToTeamCityFalse() {
        CmdLineParams params = new CmdLineParams();
        params.setCI("temacity");
        assertThat(params.isCIEqualToTeamCity(), is(false));
    }

    @Test
    public void verifyIsCIEqualToTeamCityNotSet() {
        CmdLineParams params = new CmdLineParams();
        assertThat(params.isCIEqualToTeamCity(), is(false));
    }
}