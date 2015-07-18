package uk.co.automatictester.lightning.cli;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import uk.co.automatictester.lightning.ConsoleOutputTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class CommandLineInterfaceTest extends ConsoleOutputTest {

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
    public void testIsCIEqualToTeamCityTrue(String ci) {
        CommandLineInterface params = new CommandLineInterface(new String[]{String.format("-ci=%s", ci), "-xml=src/test/resources/xml/3_0_0.xml", "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.isCIEqualTo("teamcity"), is(true));
    }

    @Test(dataProvider = "jenkins")
    public void testIsCIEqualToJenkinsTrue(String ci) {
        CommandLineInterface params = new CommandLineInterface(new String[]{String.format("-ci=%s", ci), "-xml=src/test/resources/xml/3_0_0.xml", "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.isCIEqualTo("jenkins"), is(true));
    }

    @Test
    public void testIsCIEqualToJenkinsNotSet() {
        CommandLineInterface params = new CommandLineInterface(new String[]{"-xml=src/test/resources/xml/3_0_0.xml", "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.isCIEqualTo("jenkins"), is(false));
    }

    @Test
    public void testPrintHelp() {
        String expectedOutput = String.format("Usage: java -jar lightning-<version_number>.jar [options]%n" +
                "  Options:%n" +
                "    -ci%n" +
                "       CI server (jenkins or teamcity)%n" +
                "  * -csv%n" +
                "       JMeter CSV result file%n" +
                "  * -xml%n" +
                "       Lightning XML config file");

        CommandLineInterface params = new CommandLineInterface(new String[]{"-h"});
        configureStream();
        params.printHelp();
        assertThat(out.toString(), containsString(expectedOutput));
        revertStream();
    }
}