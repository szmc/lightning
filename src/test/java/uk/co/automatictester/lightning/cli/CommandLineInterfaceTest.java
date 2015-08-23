package uk.co.automatictester.lightning.cli;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import uk.co.automatictester.lightning.ConsoleOutputTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommandLineInterfaceTest extends ConsoleOutputTest {

    @DataProvider(name = "teamcity")
    private String[][] teamCity() {
        return new String[][]{
                {"teamcity"},
                {"TeamCity"}
        };
    }

    @Test(dataProvider = "teamcity")
    public void testIsCIEqualToTeamCityTrue_verify(String ci) {
        CommandLineInterface params = new CommandLineInterface(new String[]{"verify", String.format("-ci=%s", ci), "-xml=src/test/resources/xml/3_0_0.xml", "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.verify.isCIEqualTo("teamcity"), is(true));
    }

    @Test(dataProvider = "teamcity")
    public void testIsCIEqualToTeamCityTrue_report(String ci) {
        CommandLineInterface params = new CommandLineInterface(new String[]{"report", String.format("-ci=%s", ci), "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.report.isCIEqualTo("teamcity"), is(true));
    }

    @DataProvider(name = "jenkins")
    private String[][] jenkins() {
        return new String[][]{
                {"jenkins"},
                {"Jenkins"}
        };
    }

    @Test(dataProvider = "jenkins")
    public void testIsCIEqualToJenkinsTrue_verify(String ci) {
        CommandLineInterface params = new CommandLineInterface(new String[]{"verify", String.format("-ci=%s", ci), "-xml=src/test/resources/xml/3_0_0.xml", "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.verify.isCIEqualTo("jenkins"), is(true));
    }

    @Test(dataProvider = "jenkins")
    public void testIsCIEqualToJenkinsTrue_report(String ci) {
        CommandLineInterface params = new CommandLineInterface(new String[]{"report", String.format("-ci=%s", ci), "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.report.isCIEqualTo("jenkins"), is(true));
    }

    @Test
    public void testGetParsedCommandVerify() {
        CommandLineInterface params = new CommandLineInterface(new String[]{"verify", "-xml=src/test/resources/xml/3_0_0.xml", "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.getParsedCommand(), equalToIgnoringCase("verify"));
    }

    @Test
    public void testGetParsedCommandReport() {
        CommandLineInterface params = new CommandLineInterface(new String[]{"report", "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.getParsedCommand(), equalToIgnoringCase("report"));
    }

    @Test
    public void testGetCSVFileInReportMode() {
        CommandLineInterface params = new CommandLineInterface(new String[]{"report", "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.report.getCSVFile(), equalToIgnoringCase("src/test/resources/csv/10_transactions.csv"));
    }

    @Test
    public void testGetCSVFileInVerifyMode() {
        CommandLineInterface params = new CommandLineInterface(new String[]{"verify", "-xml=src/test/resources/xml/3_0_0.xml", "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.verify.getCSVFile(), equalToIgnoringCase("src/test/resources/csv/10_transactions.csv"));
    }

    @Test
    public void testGetXMLFileInVerifyMode() {
        CommandLineInterface params = new CommandLineInterface(new String[]{"verify", "-xml=src/test/resources/xml/3_0_0.xml", "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.verify.getXmlFile(), equalToIgnoringCase("src/test/resources/xml/3_0_0.xml"));
    }

    @Test
    public void testIsCIEqualToJenkinsNotSet() {
        CommandLineInterface params = new CommandLineInterface(new String[]{"verify", "-xml=src/test/resources/xml/3_0_0.xml", "-csv=src/test/resources/csv/10_transactions.csv"});
        assertThat(params.verify.isCIEqualTo("jenkins"), is(false));
    }

    @Test
    public void testIsHelpRequested() {
        CommandLineInterface params = new CommandLineInterface(new String[]{"-h"});
        assertThat(params.isHelpRequested(), is(true));
    }

    @Test
    public void testPrintHelp() {
        String expectedOutput = String.format("Usage: java -jar lightning-<version_number>.jar [options] [command] [command options]%n" +
                "  Commands:%n" +
                "    verify      Execute Lightning tests agains JMeter output%n" +
                "      Usage: verify [options]%n" +
                "        Options:%n" +
                "          -ci%n" +
                "             CI server (jenkins or teamcity)%n" +
                "        * -csv%n" +
                "             JMeter CSV result file%n" +
                "        * -xml%n" +
                "             Lightning XML config file%n" +
                "%n" +
                "    report      Generate report on JMeter output%n" +
                "      Usage: report [options]%n" +
                "        Options:%n" +
                "          -ci%n" +
                "             CI server (jenkins or teamcity)%n" +
                "        * -csv%n" +
                "             JMeter CSV result file");

        CommandLineInterface params = new CommandLineInterface(new String[]{});
        configureStream();
        params.printHelp();
        assertThat(out.toString(), containsString(expectedOutput));
        revertStream();
    }
}